package com.ivy.vueflow.parser.bus;

import com.ivy.vueflow.parser.entity.node.Node;
import com.yomahub.liteflow.builder.el.*;

public class ELBusWhile extends BaseELBus {

    public static LoopELWrapper node(NodeELWrapper nodeElWrapper) {
        return ELBus.whileOpt(nodeElWrapper);
    }

    public static LoopELWrapper node(String nodeElWrapper) {
        return ELBus.whileOpt(nodeElWrapper);
    }

    public static LoopELWrapper node(AndELWrapper andElWrapper) {
        return ELBus.whileOpt(andElWrapper);
    }

    public static LoopELWrapper node(OrELWrapper orElWrapper) {
        return ELBus.whileOpt(orElWrapper);
    }

    public static LoopELWrapper node(NotELWrapper notElWrapper) {
        return ELBus.whileOpt(notElWrapper);
    }

//    public static WhileELWrapper node(Node node){
//        Object doOpt = node.getCmpDoOptEL() != null ? node.getCmpDoOptEL() : node.getCmpDoOpt();
//        Object breakOpt = node.getCmpBreakOptEL() != null ? node.getCmpBreakOptEL() : node.getCmpBreakOpt();
//        WhileELWrapper wrapper = ELBus.whileOpt(ELBusNode.node(node));
//        wrapper.doOpt(doOpt);
//        wrapper.breakOpt(breakOpt);
//        wrapper.parallel(node.getCmpParallel());
//        setId(wrapper, node);
//        setTag(wrapper, node);
//        setMaxWaitSeconds(wrapper, node);
//        return wrapper;
//    }

    public static LoopELWrapper wrapper(Node node, Object object){
        LoopELWrapper wrapper = null;
        if(object instanceof String){
            wrapper = ELBus.whileOpt((String) object);
        }else if(object instanceof NodeELWrapper){
            wrapper = ELBus.whileOpt((NodeELWrapper) object);
        }else if(object instanceof AndELWrapper){
            wrapper = ELBus.whileOpt((AndELWrapper) object);
        }else if(object instanceof OrELWrapper){
            wrapper = ELBus.whileOpt((OrELWrapper) object);
        }else if(object instanceof NotELWrapper){
            wrapper = ELBus.whileOpt((NotELWrapper) object);
        }
        setId(wrapper, node);
        setTag(wrapper, node);
        setMaxWaitSeconds(wrapper, node);
        setRetry(wrapper, node);
        setParallel(wrapper, node);
        return wrapper;
    }

}
