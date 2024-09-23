package com.ivy.vueflow.parser.bus;

import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.entity.node.NodeData;
import com.ivy.vueflow.parser.entity.node.NodeDataIf;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.yomahub.liteflow.builder.el.*;

public class ELBusIf extends BaseELBus {

    public static IfELWrapper node(String node, String trueOpt, String falseOpt){
        IfELWrapper ifELWrapper = ELBus.ifOpt(ELBusNode.node(node), trueOpt, falseOpt);
        return ifELWrapper;
    }

    public static IfELWrapper node(Node node) throws LiteFlowELException {
        try {
            NodeData data = node.getData();
            NodeDataIf nodeDataIf = data.getNodeDataIf();
            IfELWrapper wrapper = null;
            if(nodeDataIf != null){
                wrapper = ELBus.ifOpt(ELBusNode.node(node), nodeDataIf.getTrueOpt(), nodeDataIf.getFalseOpt());
            }
            return wrapper;
        }catch (Exception e){
            e.printStackTrace();
            throw new LiteFlowELException(
                    "param is error！未设置参数或路径！"+System.lineSeparator()+
                    "节点ID = "+node.getId()+System.lineSeparator()+
                    "组件ID = "+node.getData().getId()
            );
        }
    }

    public static IfELWrapper node(Node node, ELWrapper trueOpt, ELWrapper falseOpt) throws LiteFlowELException {
        IfELWrapper ifELWrapper = null;
        if(falseOpt != null){
            ifELWrapper = ELBus.ifOpt(ELBusNode.node(node), trueOpt, falseOpt);
        }else{
            ifELWrapper = ELBus.ifOpt(ELBusNode.node(node), trueOpt);
        }
        return ifELWrapper;
    }

    public static IfELWrapper node(String object, ELWrapper trueOpt, ELWrapper falseOpt){
        IfELWrapper ifELWrapper = null;
        if(falseOpt != null){
            ifELWrapper = ELBus.ifOpt((String) object, trueOpt, falseOpt);
        }else{
            ifELWrapper = ELBus.ifOpt((String) object, trueOpt);
        }
        return ifELWrapper;
    }

    public static IfELWrapper node(Node node, Object object, ELWrapper trueOpt, ELWrapper falseOpt) throws LiteFlowELException {
        IfELWrapper wrapper = null;
        if(object == null){
            wrapper = node(node, trueOpt, falseOpt);
        }else{
            if(object instanceof String){
                if(falseOpt != null){
                    wrapper = ELBus.ifOpt((String) object, trueOpt, falseOpt);
                }else{
                    wrapper = ELBus.ifOpt((String) object, trueOpt);
                }
            }else if(object instanceof NodeELWrapper){
                if(falseOpt != null) {
                    wrapper = ELBus.ifOpt((NodeELWrapper) object, trueOpt, falseOpt);
                }else{
                    wrapper = ELBus.ifOpt((NodeELWrapper) object, trueOpt);
                }
            }else if(object instanceof AndELWrapper){
                if(falseOpt != null) {
                    wrapper = ELBus.ifOpt((AndELWrapper) object, trueOpt, falseOpt);
                }else{
                    wrapper = ELBus.ifOpt((AndELWrapper) object, trueOpt);
                }
            }else if(object instanceof OrELWrapper){
                if(falseOpt != null) {
                    wrapper = ELBus.ifOpt((OrELWrapper) object, trueOpt, falseOpt);
                }else{
                    wrapper = ELBus.ifOpt((OrELWrapper) object, trueOpt);
                }
            }else if(object instanceof NotELWrapper){
                if(falseOpt != null) {
                    wrapper = ELBus.ifOpt((NotELWrapper) object, trueOpt, falseOpt);
                }else{
                    wrapper = ELBus.ifOpt((NotELWrapper) object, trueOpt);
                }
            }
        }

        setId(wrapper, node);
        setTag(wrapper, node);
        setMaxWaitSeconds(wrapper, node);
        setRetry(wrapper, node);
        return wrapper;
    }

}
