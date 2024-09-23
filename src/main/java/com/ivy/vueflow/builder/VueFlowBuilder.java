package com.ivy.vueflow.builder;

import cn.hutool.core.collection.CollUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ivy.vueflow.convert.bean.CmpProperty;
import com.ivy.vueflow.parser.entity.FlowData;
import com.ivy.vueflow.parser.entity.Viewport;
import com.ivy.vueflow.parser.entity.edge.Edge;
import com.ivy.vueflow.parser.entity.edge.EdgeData;
import com.ivy.vueflow.parser.entity.node.*;
import com.ivy.vueflow.parser.entity.style.NodeStyle;
import com.ivy.vueflow.parser.entity.style.NodeStyleHandles;
import com.ivy.vueflow.parser.entity.style.NodeStyleToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VueFlowBuilder implements FlowBuilder {

    private FlowData flowData = new FlowData();

    @Override
    public FlowBuilder defaultData() {
        flowData.setNodes(new ArrayList<>());
        flowData.setEdges(new ArrayList<>());
        flowData.setPosition(CollUtil.toList(0d,0d));
        flowData.setZoom(1d);
        flowData.setViewport(new Viewport(0d,0d,1d));
        return this;
    }

    @Override
    public FlowBuilder addNode(Node node) {
        flowData.getNodes().add(node);
        return this;
    }

    @Override
    public FlowBuilder addEdge(Node sourceNode, Node targetNode) {
        flowData.getEdges().add(buildEdge(sourceNode, targetNode));
        return this;
    }

    @Override
    public FlowBuilder format(boolean format) {
        flowData.setFormat(format);
        return this;
    }

    @Override
    public String build() {
        return getGson().toJson(flowData);
    }

    private Gson getGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        if(flowData.getFormat()){
            gsonBuilder.setPrettyPrinting();
        }
        return gsonBuilder.create();

    }

    private static String uuid(){
        return UUID.randomUUID().toString();
    }

    public static Node buildNode(CmpProperty property){
        String nodeId = property.getId();
        String type = property.getType();

        Node node = new Node();
        node.setId(UUID.randomUUID().toString());
        node.setType("common");
        node.setLabel(nodeId);
        node.setPosition(new Position(0d,0d));

        NodeData nodeData = new NodeData();
        nodeData.setId(nodeId);
        nodeData.setName(nodeId);
        nodeData.setType("common");
        nodeData.setMode("default");
        nodeData.setNodeDataBase(new NodeDataBase());
        nodeData.setNodeDataSwitch(new NodeDataSwitch());
        nodeData.setNodeDataIf(new NodeDataIf());

        NodeStyle nodeStyle = new NodeStyle();
        nodeStyle.setToolbar(new NodeStyleToolbar("top",false,10));
        List<NodeStyleHandles> handles = new ArrayList<>();
        handles.add(new NodeStyleHandles(UUID.randomUUID().toString(),"left",1d,"target",""));
        handles.add(new NodeStyleHandles(UUID.randomUUID().toString(),"right",1d,"source",""));
        handles.add(new NodeStyleHandles(UUID.randomUUID().toString(),"top",0d,"target",""));
        handles.add(new NodeStyleHandles(UUID.randomUUID().toString(),"bottom",0d,"source",""));
        nodeStyle.setHandles(handles);
        nodeStyle.setExtendHandles(new ArrayList<>());

        nodeData.setStyle(nodeStyle);

        node.setData(nodeData);
        node.setParentNode(null);
        node.setInitialized(false);

        property.setNode(node);
        return node;
    }

    public static Edge buildEdge(CmpProperty source, CmpProperty target){
        return buildEdge(source.getNode(), target.getNode());
    }

    public static Edge buildEdge(Node source, Node target){
        Edge edge = new Edge();
        edge.setId(uuid());
        edge.setType("animation");
        edge.setSource(source.getId());
        edge.setTarget(target.getId());
//        edge.setSourceHandle();
//        edge.setTargetHandle();
        edge.setData(new EdgeData());
        edge.setLabel("");
        edge.setAnimated(true);
        edge.setUpdatable(true);
        edge.setMarkerEnd("arrowclosed");
//        edge.setSourceX();
//        edge.setSourceY();
//        edge.setTargetX();
//        edge.setTargetY();
//        edge.setStyle();
//        edge.setLabelStyle();
//        edge.setLabelBgPadding();
//        edge.setLabelBgBorderRadius();
//        edge.setLabelBgStyle();
        return edge;
    }
}
