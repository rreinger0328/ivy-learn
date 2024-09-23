package com.ivy.vueflow.parser.graph;

import cn.hutool.core.collection.CollUtil;
import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.ivy.vueflow.parser.nodes.ThenNode;
import com.yomahub.liteflow.builder.el.ELWrapper;

import java.util.List;

public class GraphToEL2 {

    public static ELWrapper toEL(Graph graph) throws LiteFlowELException {
        ELWrapper wrapper = null;
        List<Node> startNodes = graph.getStartNodes();
        if(CollUtil.isNotEmpty(startNodes)){
            if(startNodes.size() == 1){
                Node node = startNodes.get(0);
                switch (node.getType()){
                    case "then": return ThenNode.toEL(node, graph);
                    case "common": return ThenNode.toEL(node, graph);
                }
            }else{

            }
        }
        /*if(wrapper != null){
            ValidationResp resp = LiteFlowChainELBuilder.validateWithEx(wrapper.toEL());
            if (!resp.isSuccess()){
                System.out.println(resp.getCause());
            }
        }*/
        return wrapper;
    }
}
