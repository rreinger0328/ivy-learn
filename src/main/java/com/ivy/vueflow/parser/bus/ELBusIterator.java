package com.ivy.vueflow.parser.bus;

import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.yomahub.liteflow.builder.el.ELBus;
import com.yomahub.liteflow.builder.el.LoopELWrapper;
import com.yomahub.liteflow.builder.el.NodeELWrapper;

public class ELBusIterator extends BaseELBus {

    public static LoopELWrapper node(NodeELWrapper nodeElWrapper) {
        return ELBus.iteratorOpt(nodeElWrapper);
    }

    public static LoopELWrapper node(String nodeElWrapper) {
        return ELBus.iteratorOpt(nodeElWrapper);
    }

    public static LoopELWrapper node(Node node) throws LiteFlowELException {
        LoopELWrapper wrapper = ELBus.iteratorOpt(ELBusNode.node(node));
        setId(wrapper, node);
        setTag(wrapper, node);
        setMaxWaitSeconds(wrapper, node);
        setRetry(wrapper, node);
        setParallel(wrapper, node);
        return wrapper;
    }

//    public static IteratorELWrapper node(Node node){
//        Object doOpt = node.getCmpDoOptEL() != null ? node.getCmpDoOptEL() : node.getCmpDoOpt();
//        IteratorELWrapper wrapper = ELBus.iteratorOpt(ELBusNode.node(node));
//        wrapper.doOpt(doOpt);
//        wrapper.parallel(node.getCmpParallel());
//        setId(wrapper, node);
//        setTag(wrapper, node);
//        setMaxWaitSeconds(wrapper, node);
//        return wrapper;
//    }

}
