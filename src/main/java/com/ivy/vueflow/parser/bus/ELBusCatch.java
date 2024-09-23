package com.ivy.vueflow.parser.bus;

import com.yomahub.liteflow.builder.el.CatchELWrapper;
import com.yomahub.liteflow.builder.el.ELBus;

public class ELBusCatch extends BaseELBus {

    public static CatchELWrapper catchException(Object object){
        return ELBus.catchException(object);
    }

//    public static CatchELWrapper catchException(Node node){
//        CatchELWrapper wrapper = ELBus.catchException(node.getCatchEL());
//        setId(wrapper, node);
//        setTag(wrapper, node);
//        setMaxWaitSeconds(wrapper, node);
//        setDoOpt(wrapper, node);
//        return wrapper;
//    }
}
