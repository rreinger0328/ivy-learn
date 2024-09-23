package com.ivy.vueflow.parser.bus;

import com.yomahub.liteflow.builder.el.*;

public class ELBusNot extends BaseELBus {

    public static NotELWrapper node(NodeELWrapper notElWrapper) {
        return ELBus.not(notElWrapper);
    }

    public static NotELWrapper node(String notElWrapper) {
        return ELBus.not(notElWrapper);
    }

    public static NotELWrapper node(AndELWrapper notElWrapper) {
        return ELBus.not(notElWrapper);
    }

    public static NotELWrapper node(OrELWrapper notElWrapper) {
        return ELBus.not(notElWrapper);
    }

    public static NotELWrapper node(NotELWrapper notElWrapper) {
        return ELBus.not(notElWrapper);
    }

//    public static NotELWrapper node(Node node){
//        NotELWrapper wrapper = node(node.getNotEL());
//        setId(wrapper, node);
//        setTag(wrapper, node);
//        setMaxWaitSeconds(wrapper, node);
//        return wrapper;
//    }

    public static NotELWrapper not(Object object){
        if(object instanceof String){
            return node((String) object);
        }else if(object instanceof NodeELWrapper){
            return node((NodeELWrapper) object);
        }else if(object instanceof AndELWrapper){
            return node((AndELWrapper) object);
        }else if(object instanceof OrELWrapper){
            return node((OrELWrapper) object);
        }else if(object instanceof NotELWrapper){
            return node((NotELWrapper) object);
        }
        throw new RuntimeException("参数类型错误！");
    }
}
