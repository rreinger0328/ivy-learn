package com.ivy.vueflow.load;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.entity.node.NodeData;
import com.ivy.vueflow.parser.entity.node.NodeDataBase;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.ivy.vueflow.parser.graph.Graph;
import com.yomahub.liteflow.builder.LiteFlowNodeBuilder;
import groovy.lang.GroovyClassLoader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LiteFlowNodeLoad {

    private static final List<String> typeList = ListUtil.toList("common","switch","if","while","boolean","for","iterator");

    public static void loadAndBuild(Graph graph) throws LiteFlowELException {
        Map<String, Node> nodeMap = graph.getNodes();
        // 过滤无需构建的节点
        List<Node> nodes = nodeMap.values().stream().filter(m-> typeList.contains(m.getType())).collect(Collectors.toList());
        for (Node node : nodes){
            if("for".equals(node.getType()) && node.getData().getNodeDataLoop().getLoopNumber() != null){
                //无需构建节点
            }else{
                loadAndBuild(node);
            }
        }
    }

    public static void loadAndBuild(Node node) throws LiteFlowELException {
        try {
            NodeData data = node.getData();
            NodeDataBase nodeDataBase = data.getNodeDataBase();
            Integer classType = nodeDataBase.getClassType();
            if(classType != null){
                if(classType == 0){
                    buildCmp(node);
                } else if (classType == 1) {
                    buildSpringBean(node);
                } else if (classType == 2) {
                    buildDynamicCmp(node);
                } else if (classType == 3) {
                    buildScript(node);
                }else{
                    throw new LiteFlowELException("["+data.getId()+" - "+data.getName()+"]classType设置错误!");
                }
            }else{
                throw new LiteFlowELException("["+data.getId()+" - "+data.getName()+"]未设置classType!");
            }
        } catch (LiteFlowELException e) {
            System.out.println(ExceptionUtil.stacktraceToString(e));
            throw new LiteFlowELException(e.getMessage());
        } catch (Exception e) {
            System.out.println(ExceptionUtil.stacktraceToString(e));
            e.printStackTrace();
        }
    }

    private static void buildCmp(Node node) throws LiteFlowELException {
        checkClazz(node.getData());
        NodeData data = node.getData();
        String clazz = data.getNodeDataBase().getClazz();
        Class<?> aClass = null;
        try {
            aClass = Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        SpringUtil.registerBean(data.getId(), aClass);
        buildNode(node, aClass);
    }

    private static void buildSpringBean(Node node) throws LiteFlowELException {
        checkClazz(node.getData());
        buildNode(node, null);
    }

    private static void buildDynamicCmp(Node node) throws LiteFlowELException {
        checkCode(node.getData());
        NodeData data = node.getData();
        NodeDataBase nodeDataBase = data.getNodeDataBase();
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        Class clazz = groovyClassLoader.parseClass(nodeDataBase.getCode());
        SpringBeanUtil.replace(data.getId(), clazz);
        buildNode(node, clazz);
    }

    private static void buildScript(Node node) throws LiteFlowELException {
        checkCode(node.getData());
        NodeData nodeData = node.getData();
        NodeDataBase nodeDataBase = nodeData.getNodeDataBase();
        LiteFlowNodeBuilder builder = null;
        switch (nodeData.getType()){
            case "common": builder = LiteFlowNodeBuilder.createScriptNode();break;
            case "switch": builder = LiteFlowNodeBuilder.createScriptSwitchNode();break;
            case "if":
            case "while":
            case "boolean": builder = LiteFlowNodeBuilder.createScriptBooleanNode();break;
            case "for": builder = LiteFlowNodeBuilder.createScriptForNode();break;
            case "iterator": throw new LiteFlowELException("["+nodeData.getId()+" - "+nodeData.getName()+"]不支持构建[迭代循环脚本组件]!");
        }
        if(builder != null){
            builder.setId(nodeData.getId())
                    .setLanguage(nodeDataBase.getLanguage())
                    .setScript(nodeDataBase.getCode())
                    .build();
            System.out.println("["+nodeData.getId()+" - "+nodeData.getName()+"]脚本组件构建成功!");
        }else{
            throw new LiteFlowELException("["+nodeData.getId()+" - "+nodeData.getName()+"]脚本组件构建失败!");
        }
    }

    private static void buildNode(Node node, Class<?> clazz) throws LiteFlowELException {
        NodeData nodeData = node.getData();
        NodeDataBase nodeDataBase = nodeData.getNodeDataBase();
        LiteFlowNodeBuilder builder = null;
        switch (nodeData.getType()){
            case "common": builder = LiteFlowNodeBuilder.createCommonNode();break;
            case "switch": builder = LiteFlowNodeBuilder.createSwitchNode();break;
            case "if":
            case "while":
            case "boolean": builder = LiteFlowNodeBuilder.createBooleanNode();break;
            case "for": builder = LiteFlowNodeBuilder.createForNode();break;
            case "iterator": builder = LiteFlowNodeBuilder.createIteratorNode();break;
        }
        if(builder != null){
            builder.setId(nodeData.getId());
            if(clazz != null){
                builder.setClazz(clazz);
            }else{
                builder.setClazz(nodeDataBase.getClazz());
            }
            builder.build();
            System.out.println("["+nodeData.getId()+" - "+nodeData.getName()+"]组件构建成功!");
        }else{
            throw new LiteFlowELException("["+nodeData.getId()+" - "+nodeData.getName()+"]组件构建失败!");
        }
    }

    private static void checkClazz(NodeData data) throws LiteFlowELException {
        if(StrUtil.isBlank(data.getNodeDataBase().getClazz())){
            throw new LiteFlowELException("["+data.getId()+" - "+data.getName()+"]未设置class!");
        }
    }

    private static void checkCode(NodeData data) throws LiteFlowELException {
        if(StrUtil.isBlank(data.getNodeDataBase().getCode())){
            throw new LiteFlowELException("["+data.getId()+" - "+data.getName()+"]未设置代码!");
        }
    }
}
