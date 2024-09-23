package com.ivy.vueflow.parser.nodes;

import cn.hutool.core.util.StrUtil;
import com.ivy.vueflow.parser.bus.ELBusThen;
import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.entity.node.NodeDataThen;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.ivy.vueflow.parser.graph.Graph;
import com.ivy.vueflow.parser.graph.GraphUtil;
import com.yomahub.liteflow.builder.el.ELWrapper;
import com.yomahub.liteflow.builder.el.ThenELWrapper;

import java.util.List;
import java.util.stream.Collectors;

public class ThenNode extends BaseNode {

    public static ELWrapper toEL(Node node, Graph graph) throws LiteFlowELException {
        ThenELWrapper wrapper = ELBusThen.then();
        thenProcess(wrapper, node);
        catchProcess(wrapper, node);

        List<List<Node>> allPaths = graph.findAllPaths(node,false);
        List<Node> segmentationPoints = GraphUtil.findSegmentationPoints(allPaths, graph.getNodes());
        List<List<Node>> processSegments = GraphUtil.getProcessSegments(allPaths, segmentationPoints);
        List<Node> thenNodeList = segmentationPoints.stream().filter(m -> "then".equalsIgnoreCase(m.getType())).collect(Collectors.toList());

        if("then".equalsIgnoreCase(node.getType())){

            //获取下一个then
            Node nextThenNode = thenNodeList.stream().filter(m -> m != node).findFirst().orElse(null);
            if(nextThenNode != null){

            }else{
                List<Node> neighbors = graph.getNeighbors(node);
                if(neighbors.size() == 1){
                    //wrapper.then(ThenNode.toEL(neighbors.get(0), graph));
                }
            }
            System.out.println();
        }else if("common".equalsIgnoreCase(node.getType())){
            for (List<Node> items : processSegments){
                if(items.size() == 1){
                    elWrapper(wrapper, items.get(0), graph);
                }
            }
        }
        return wrapper;
    }

    private static void thenProcess(ThenELWrapper thenELWrapper,Node thenNode){
        if(thenNode != null && "then".equalsIgnoreCase(thenNode.getType())){
            NodeDataThen nodeData = thenNode.getData().getNodeDataThen();
            if(StrUtil.isNotBlank(nodeData.getId())){
                thenELWrapper.id(nodeData.getId());
            }
            if(StrUtil.isNotBlank(nodeData.getTag())){
                thenELWrapper.tag(nodeData.getTag());
            }
        }
    }

    public static ThenELWrapper elWrapper(ThenELWrapper wrapper, Node node, Graph graph) throws LiteFlowELException {
        thenProcess(wrapper, node);
        catchProcess(wrapper, node);
        switch (node.getType()){
            case "common": return wrapper.then(CommonNode.toEL(node, graph));
        }
        return wrapper;
    }

}
