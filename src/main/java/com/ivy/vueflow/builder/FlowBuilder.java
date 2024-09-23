package com.ivy.vueflow.builder;

import com.ivy.vueflow.parser.entity.node.Node;

public interface FlowBuilder {


    FlowBuilder defaultData();

    FlowBuilder addNode(Node node);

    FlowBuilder addEdge(Node sourceNode, Node targetNode);

    FlowBuilder format(boolean format);

    String build();

}
