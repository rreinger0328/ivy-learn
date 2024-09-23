package com.ivy.vueflow.parser.bus;

import com.yomahub.liteflow.builder.el.ELBus;
import com.yomahub.liteflow.builder.el.OrELWrapper;

public class ELBusOr extends BaseELBus {

    public static OrELWrapper node(Object... objects){
        return ELBus.or(objects);
    }

    public static OrELWrapper node(){
        OrELWrapper wrapper = ELBus.or();
        return wrapper;
    }

//    public static OrELWrapper node(Node node){
//        OrELWrapper wrapper = ELBus.or(node.getOrEL());
//        setId(wrapper, node);
//        setTag(wrapper, node);
//        setMaxWaitSeconds(wrapper, node);
//        return wrapper;
//    }

}
