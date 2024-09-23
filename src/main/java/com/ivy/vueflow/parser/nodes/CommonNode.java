package com.ivy.vueflow.parser.nodes;

import com.ivy.vueflow.parser.bus.ELBusNode;
import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.ivy.vueflow.parser.graph.Graph;
import com.yomahub.liteflow.builder.el.ELWrapper;

public class CommonNode {
    public static ELWrapper toEL(Node node, Graph graph) throws LiteFlowELException {
        return ELBusNode.node(node);
    }
}
