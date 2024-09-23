package com.ivy.vueflow.parser.graph;

import cn.hutool.core.collection.CollUtil;
import com.ivy.vueflow.parser.entity.edge.Edge;
import com.ivy.vueflow.parser.entity.node.Node;

import java.util.*;
import java.util.stream.Collectors;

public class GraphUtil {

    public static List<List<Node>> getProcessSegments(List<List<Node>> allPaths, List<Node> segmentationPoints) {
        // 每个分段点初始化一个独立的段
        List<List<Node>> segments = new ArrayList<>();
        try {
            Set<String> segmentPointsSet = new LinkedHashSet<>();
            for (Node node : segmentationPoints) {
                segmentPointsSet.add(node.id);
            }

            Map<String,Node> segmentationPointsMap = segmentationPoints.stream().collect(Collectors.toMap(Node::getId, node -> node));

            // 初始化存储每个分段点之后节点的集合
            Map<String, List<Node>> segmentsAfterSegmentPoint = new LinkedHashMap<>();
            // 初始化存储每个分段点之前节点的集合
            Map<String, List<Node>> segmentsBeforeSegmentPoint = new LinkedHashMap<>();

            for (Node point : segmentationPoints) {
                segmentsAfterSegmentPoint.put(point.id, new ArrayList<>());
            }

            Node firstNode = segmentationPoints.get(0);
            Set<Node> prevNodeSet = new LinkedHashSet<>();
            // 遍历所有路径
            for (List<Node> path : allPaths) {
                String lastSegmentPointId = null;
                boolean flag = true;
                for (Node currentNode : path) {
                    if(flag && !currentNode.id.equals(firstNode.id)){
                        prevNodeSet.add(currentNode);
                    }
                    if(currentNode.id.equals(firstNode.id)){
                        flag = false;
                    }
                    if (segmentPointsSet.contains(currentNode.id)) {
                        lastSegmentPointId = currentNode.id;  // 更新最近的分段点
                    } else if (lastSegmentPointId != null) {
                        // 将节点添加到最近的分段点之后的集合中
                        if (!segmentsAfterSegmentPoint.get(lastSegmentPointId).contains(currentNode)) {
                            segmentsAfterSegmentPoint.get(lastSegmentPointId).add(currentNode);
                        }
                    }
                }
            }

            if(!prevNodeSet.isEmpty()){
                segments.add(new ArrayList<>(prevNodeSet));
            }
            // 根据分段点顺序，将分段点之后的节点添加到段中
            for (String segmentPointId : segmentsAfterSegmentPoint.keySet()) {
                Node currNode = segmentationPointsMap.get(segmentPointId);
                segments.add(Collections.singletonList(currNode));
                List<Node> segmentNodes = segmentsAfterSegmentPoint.get(segmentPointId);
                if (!segmentNodes.isEmpty()) {
                    segments.add(segmentNodes);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return segments;
    }

    //合并连续的、元素个数为1集合
    public static List<List<Node>> mergeSingleElementSegments(List<List<Node>> segments) {
        List<List<Node>> mergedSegments = new ArrayList<>();
        if (segments == null || segments.isEmpty()) {
            return mergedSegments;
        }

        List<Node> currentSegment = new ArrayList<>();

        for (List<Node> segment : segments) {
            if (segment.size() == 1) {
                // 如果当前段只有一个元素，则添加到当前正在合并的段中
                currentSegment.addAll(segment);
            } else {
                // 如果当前段有多个元素或者为空，首先检查是否有正在合并的段需要添加到结果列表中
                if (!currentSegment.isEmpty()) {
                    mergedSegments.add(new ArrayList<>(currentSegment));
                    currentSegment.clear();
                }
                // 将当前段添加到结果列表中
                mergedSegments.add(new ArrayList<>(segment));
            }
        }

        // 检查最后是否还有剩余的正在合并的段未添加
        if (!currentSegment.isEmpty()) {
            mergedSegments.add(currentSegment);
        }

        return mergedSegments;
    }


    //获取分段节点集合
    public static List<Node> findSegmentationPoints(List<List<Node>> paths, Map<String, Node> nodes) {
        Map<String, Integer> nodeCount = new LinkedHashMap<>();
        for (List<Node> path : paths) {
            Set<String> uniqueNodes = new LinkedHashSet<>();
            for (Node node : path) {
                uniqueNodes.add(node.getId());
            }
            uniqueNodes.forEach(id -> nodeCount.put(id, nodeCount.getOrDefault(id, 0) + 1));
        }

        List<Node> segmentationPoints = new ArrayList<>();
        int totalPaths = paths.size();
        nodeCount.forEach((id, count) -> {
            if (count == totalPaths) {
                segmentationPoints.add(nodes.get(id));
            }
        });

        return segmentationPoints;
    }

    // 辅助的DFS方法
    public static void dfs(String nodeId, Set<String> visited, Map<String, List<String>> adjList) {
        visited.add(nodeId);
        List<String> neighbors = adjList.get(nodeId);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, visited, adjList);
                }
            }
        }
    }

    // 递归查找所有路径
    public static void findPathsRecursive(String current, String end, List<Node> path, List<List<Node>> paths, Set<String> visited, Map<String, List<String>> adjList, Map<String, Node> nodes) {
        path.add(nodes.get(current));
        visited.add(current);

        if (current.equals(end)) {
            paths.add(new ArrayList<>(path));
        } else if (adjList.containsKey(current)) {
            for (String neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    findPathsRecursive(neighbor, end, path, paths, visited, adjList, nodes);
                }
            }
        }

        path.remove(path.size() - 1);
        visited.remove(current);
    }

    //找到所有路径的方法
    public static void findAllPathsHelper(Node node, List<Node> path, List<List<Node>> allPaths, Map<String, List<String>> adjList, Map<String, Node> nodes) {
        path.add(node);
        if (!adjList.containsKey(node.getId()) || adjList.get(node.getId()).isEmpty()) {
            allPaths.add(new ArrayList<>(path));
        } else {
            for (String neighborId : adjList.get(node.getId())) {
                Node neighbor = nodes.get(neighborId);
                if (!path.contains(neighbor)) {
                    findAllPathsHelper(neighbor, new ArrayList<>(path), allPaths, adjList, nodes);
                }
            }
        }
        path.remove(node);
    }

    //获取开始节点集合，根据sourcs和targets
    public static List<Node> getStartNodes(Set<String> targets, List<Node> nodes) {
        List<Node> startNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (!targets.contains(node.getId())) {
                startNodes.add(node);
            }
        }
        // 如果没有开始节点，则将所有节点都视为开始节点
        if (startNodes.isEmpty()) {
            startNodes.addAll(nodes);
        }
        return startNodes;
    }

    //获取开始结束集合，根据sourcs和targets
    public static List<Node> getEndNodes(Set<String> sources, List<Node> nodes) {
        List<Node> endNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (!sources.contains(node.getId())) {
                endNodes.add(node);
            }
        }
        return endNodes;
    }


    //判断一个节点是否是单节点，即节点在data.getEdges的source和target中都没有出现
    public static boolean isSingle(Node node, List<Edge> edges){
        for(Edge edge : edges){
            if(edge.getSource().equals(node.getId()) || edge.getTarget().equals(node.getId())){
                return false;
            }
        }
        return true;
    }

    // 取出所有路径中相同的开始节点
    public static List<Node> startSameNode(List<List<Node>> allPaths) {
        List<Node> startNodes = new ArrayList<>();
        if (allPaths.isEmpty()) {
            return startNodes;
        }
        List<Node> firstPath = allPaths.get(0);
        for (Node node : firstPath) {
            boolean allContain = true;
            for (List<Node> path : allPaths) {
                if (!path.contains(node)) {
                    allContain = false;
                    break;
                }
            }
            if (allContain) {
                startNodes.add(node);
            }
        }
        return startNodes;
    }

    // 取出所有路径中 排除相同的开始节点
    public static List<Node> excludeSameNode(List<List<Node>> allPaths,List<Node> excludeNodes) {
        // 使用stream将allPaths转为List<Node>
        List<Node> nodeList = allPaths.stream()
                .flatMap(List::stream) // 将每个内部列表的流合并为一个流
                .collect(Collectors.toList());// 收集结果到一个新的列表
        // nodeList去掉excludeNodes中的节点
        if(CollUtil.isNotEmpty(excludeNodes)){
            nodeList = nodeList.stream().filter(node -> !excludeNodes.contains(node)).collect(Collectors.toList());
        }
        // 先转set去重，再转list
        return new ArrayList<>(new LinkedHashSet<>(nodeList));
    }

    // 判断allPaths是否存在相交点，并将有交点的路径分为一组，返回List<List<List<Node>>>
    // 判断路径是否存在交点，并按交点分组
    public static List<List<List<Node>>> groupPathsByIntersection(List<List<Node>> allPaths) {
        List<List<List<Node>>> groupedPaths = new ArrayList<>();
        Map<Node, Set<Integer>> nodeToPathIndices = new HashMap<>();

        // 记录每个节点出现在哪些路径中
        for (int i = 0; i < allPaths.size(); i++) {
            List<Node> path = allPaths.get(i);
            for (Node node : path) {
                nodeToPathIndices.putIfAbsent(node, new HashSet<>());
                nodeToPathIndices.get(node).add(i);
            }
        }

        Set<Integer> visitedPathIndices = new HashSet<>();

        // 检查并分组有交点的路径
        for (int i = 0; i < allPaths.size(); i++) {
            if (!visitedPathIndices.contains(i)) {
                List<List<Node>> group = new ArrayList<>();
                Set<Integer> currentGroupIndices = new HashSet<>();
                currentGroupIndices.add(i);
                group.add(new ArrayList<>(allPaths.get(i)));
                visitedPathIndices.add(i);

                // 寻找与当前路径有交点的其他路径
                for (Node node : allPaths.get(i)) {
                    if (nodeToPathIndices.containsKey(node)) {
                        for (Integer index : nodeToPathIndices.get(node)) {
                            if (!visitedPathIndices.contains(index)) {
                                currentGroupIndices.add(index);
                                group.add(new ArrayList<>(allPaths.get(index)));
                                visitedPathIndices.add(index);
                            }
                        }
                    }
                }

                groupedPaths.add(group);
            }
        }

        return groupedPaths;
    }

    public static List<List<Node>> findAllPathByNodes(List<Node> nodes, Graph graph) {
        Set<Node> nodeSet = new HashSet<>(nodes); // 为了快速查找，将列表转换为集合
        List<List<Node>> filteredPaths = new ArrayList<>();
        Set<String> seenPaths = new HashSet<>(); // 用于避免添加重复的路径
        Graph newGraph = new Graph(nodes, graph.getEdges(nodes));
        // 遍历所有路径
        for (List<Node> originalPath : newGraph.getAllPaths()) {
            List<Node> path = new ArrayList<>();
            for (Node node : originalPath) {
                if (nodeSet.contains(node)) {
                    path.add(node);
                }
            }
            // 将节点列表转换为字符串以检查重复
            String pathKey = path.stream().map(Node::getId).reduce("", (acc, id) -> acc + "-" + id);
            if (!path.isEmpty() && seenPaths.add(pathKey)) { // 检查路径是否为空，并且是否未被添加过
                filteredPaths.add(path);
            }
        }
        return filteredPaths;
    }

    public static boolean isSingleNode(List<List<Node>> paths) {
        List<Node> list = paths.stream().flatMap(List::stream).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(list) && list.size() == 1){
            return true;
        }
        return false;
    }

    public static Node getSingleNode(List<List<Node>> paths) {
        List<Node> list = paths.stream().flatMap(List::stream).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(list) && list.size() == 1){
            return list.get(0);
        }
        return null;
    }

//    public static List<List<Node>> findAllPathByNodes(List<Node> nodes, Graph graph) {
//        List<List<Node>> allPaths = new ArrayList<>();
//        Set<Node> nodeSet = new HashSet<>(nodes);
//        Set<Node> startNodes = findStartNodes(nodes, graph);
//
//        // 为每个起点节点初始化路径搜索
//        for (Node startNode : startNodes) {
//            List<Node> currentPath = new ArrayList<>();
//            dfs(startNode, currentPath, allPaths, nodeSet, graph);
//        }
//        return allPaths;
//    }
//
//    // 根据节点集合获取起始节点，如果只有一个节点，则直接返回
//    private static Set<Node> findStartNodes(List<Node> nodes, Graph graph) {
//        if(nodes.size() == 1){
//            return new LinkedHashSet<>(nodes);
//        }
//        Set<Node> startNodes = new LinkedHashSet<>();
//        for (Node node : nodes) {
//            List<Node> prev = graph.getPrev(node);
//            if(!nodes.contains(prev.get(0))){
//                startNodes.add(node);
//            }
//        }
//        return startNodes;
//    }
//
//    private static void dfs(Node current, List<Node> path, List<List<Node>> allPaths, Set<Node> nodeSet, Graph graph) {
//        path.add(current);
//        allPaths.add(new ArrayList<>(path)); // 保存当前路径的副本
//        List<Node> neighbors = graph.getNeighbors(current);
//        for (Node neighbor : neighbors) {
//            if (!path.contains(neighbor) && nodeSet.contains(neighbor)) {
//                dfs(neighbor, path, allPaths, nodeSet, graph);
//            }
//        }
//        path.remove(path.size() - 1); // 回溯
//    }

}
