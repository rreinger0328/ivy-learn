package com.ivy.vueflow.parser.bus;

import com.ivy.vueflow.parser.entity.edge.Edge;
import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.yomahub.liteflow.builder.el.ELBus;
import com.yomahub.liteflow.builder.el.ThenELWrapper;

public class ELBusThen extends BaseELBus {

    public static ThenELWrapper node(Object... objects){
        return ELBus.then(objects);
    }

    public static ThenELWrapper node(Node node) throws LiteFlowELException {
        return ELBus.then(ELBusNode.node(node));
    }

    public static ThenELWrapper then() {
        return ELBus.then();
    }

    public static ThenELWrapper then(Edge edge) {
        ThenELWrapper wrapper = ELBus.then();
        if(edge != null && edge.getData() != null){
            setId(wrapper, edge.getData().getId());
            setTag(wrapper, edge.getData().getTag());
        }
        return wrapper;
    }

//    public static ThenELWrapper node(Node node){
//        ThenELWrapper wrapper = ELBus.then(ELBusNode.node(node));
//        if(node.getPreEL() != null){
//            wrapper.pre(node.getPreEL());
//        }else if(StrUtil.isNotBlank(node.getCmpPre())){
//            wrapper.pre(node.getCmpPre());
//        }
//        if(node.getFinallyEL() != null) {
//            wrapper.finallyOpt(node.getFinallyEL());
//        }else if(StrUtil.isNotBlank(node.getCmpFinallyOpt())){
//            wrapper.finallyOpt(node.getCmpFinallyOpt());
//        }
//        setId(wrapper, node);
//        setTag(wrapper, node);
//        setMaxWaitSeconds(wrapper, node);
//        return wrapper;
//    }

}
