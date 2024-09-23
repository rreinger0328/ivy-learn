package com.ivy.vueflow.parser.bus;

import cn.hutool.core.util.StrUtil;
import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.entity.node.NodeData;
import com.ivy.vueflow.parser.entity.node.NodeDataSwitch;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.yomahub.liteflow.builder.el.ELBus;
import com.yomahub.liteflow.builder.el.SwitchELWrapper;

public class ELBusSwitch extends BaseELBus {

//    private ELWrapper wrapper;
//
//    public static ELBusSwitch NEW(){
//        return new ELBusSwitch();
//    }

    public static SwitchELWrapper node(String node){
        SwitchELWrapper switchELWrapper = ELBus.switchOpt(ELBusNode.node(node));
        return switchELWrapper;
    }

    public static SwitchELWrapper node(Node node) throws LiteFlowELException {
        SwitchELWrapper wrapper = ELBus.switchOpt(ELBusNode.node(node));
        NodeData data = node.getData();
        NodeDataSwitch nodeDataSwitch = data.getNodeDataSwitch();
        if(nodeDataSwitch != null){
            String nodeDataSwitchTo = nodeDataSwitch.getToOpt();
            if(StrUtil.isNotBlank(nodeDataSwitchTo)){
                if(nodeDataSwitchTo.contains(",")){
                    Object[] toArray = nodeDataSwitchTo.split(",");
                    wrapper.to(toArray);
                }else{
                    wrapper.to(nodeDataSwitchTo);
                }
            }
            if(StrUtil.isNotBlank(nodeDataSwitch.getDefaultOpt())) {
                wrapper.defaultOpt(nodeDataSwitch.getDefaultOpt());
            }
        }
        return wrapper;
    }

//    public ELBusSwitch node(Node node){
//
//        SwitchELWrapper switchELWrapper = ELBus.switchOpt(ELBusNode.node(node));
//        if(StrUtil.isNotBlank(node.getCmpId())){
//            switchELWrapper.id(node.getCmpId());
//        }
//        if(StrUtil.isNotBlank(node.getCmpTag())){
//            switchELWrapper.tag(node.getCmpTag());
//        }
//        if(node.getCmpToEL() == null){
//            String cmpTo = node.getCmpTo();
//            String[] split = null;
//            if(StrUtil.isNotBlank(cmpTo)){
//                split = cmpTo.split(",");
//            }
//            if(split != null){
//                switchELWrapper.to(split);
//            }
//        }else{
//            List<ThenELWrapper> thenELWrapperList = (List<ThenELWrapper>) node.getCmpToEL();
//            for (ThenELWrapper thenELWrapper : thenELWrapperList){
//                switchELWrapper.to(thenELWrapper);
//            }
//        }
//        if(node.getCmpDefaultOptEL() != null){
//            switchELWrapper.defaultOpt(node.getCmpDefaultOptEL());
//        }else if(StrUtil.isNotBlank(node.getCmpDefaultOpt())){
//            switchELWrapper.defaultOpt(node.getCmpDefaultOpt());
//        }
//
//        if(StrUtil.isNotBlank(node.getCmpPre()) || StrUtil.isNotBlank(node.getCmpFinallyOpt())){
//            ThenELWrapper then = ELBus.then(switchELWrapper);
//            if(StrUtil.isNotBlank(node.getCmpPre())){
//                then.pre(node.getCmpPre());
//            }
//            if(StrUtil.isNotBlank(node.getCmpFinallyOpt())){
//                then.finallyOpt(node.getCmpFinallyOpt());
//            }
//            if(node.getCmpMaxWaitSeconds() != null){
//                then.maxWaitSeconds(node.getCmpMaxWaitSeconds());
//            }
//            wrapper = then;
//        }else{
//            if(node.getCmpMaxWaitSeconds() != null){
//                switchELWrapper.maxWaitSeconds(node.getCmpMaxWaitSeconds());
//            }
//            wrapper = switchELWrapper;
//        }
//        return this;
//    }

//    public String toEL(){
//        return wrapper.toEL();
//    }
//
//    public String toEL(boolean format){
//        return wrapper.toEL(format);
//    }
//
//    public ELWrapper toELWrapper(){
//        return wrapper;
//    }

}
