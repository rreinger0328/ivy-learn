package com.ivy.vueflow.parser.bus;

import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.entity.node.NodeDataLoop;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.yomahub.liteflow.builder.el.ELBus;
import com.yomahub.liteflow.builder.el.LoopELWrapper;
import com.yomahub.liteflow.builder.el.NodeELWrapper;

public class ELBusFor extends BaseELBus {

    public static LoopELWrapper node(NodeELWrapper nodeElWrapper) {
        return ELBus.forOpt(nodeElWrapper);
    }

    public static LoopELWrapper node(String node) {
        return ELBus.forOpt(node);
    }

    public static LoopELWrapper node(Node node) throws LiteFlowELException {
        LoopELWrapper wrapper = null;
        NodeDataLoop nodeDataLoop = node.getData().getNodeDataLoop();
        if(nodeDataLoop != null && nodeDataLoop.getLoopNumber() != null){
            wrapper = ELBus.forOpt(nodeDataLoop.getLoopNumber());
        }else{
            wrapper = ELBus.forOpt(ELBusNode.node(node));
        }
        setId(wrapper, node);
        setTag(wrapper, node);
        setMaxWaitSeconds(wrapper, node);
        setRetry(wrapper, node);
        setParallel(wrapper, node);
        return wrapper;
    }

//    public static ForELWrapper node(Node node){
//        Object doOpt = node.getCmpDoOptEL() != null ? node.getCmpDoOptEL() : node.getCmpDoOpt();
//        Object breakOpt = node.getCmpBreakOptEL() != null ? node.getCmpBreakOptEL() : node.getCmpBreakOpt();
//        ForELWrapper wrapper = ELBus.forOpt(ELBusNode.node(node));
//        wrapper.doOpt(doOpt);
//        wrapper.breakOpt(breakOpt);
//        wrapper.parallel(node.getCmpParallel());
//        setId(wrapper, node);
//        setTag(wrapper, node);
//        setMaxWaitSeconds(wrapper, node);
//        return wrapper;
//    }

}
