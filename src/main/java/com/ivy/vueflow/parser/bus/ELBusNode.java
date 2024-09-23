package com.ivy.vueflow.parser.bus;

import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.yomahub.liteflow.builder.el.ELBus;
import com.yomahub.liteflow.builder.el.NodeELWrapper;

public class ELBusNode extends BaseELBus {

    public static NodeELWrapper node(String nodeId){
        return ELBus.node(nodeId);
    }

    public static NodeELWrapper node(Node node) throws LiteFlowELException {
        NodeELWrapper wrapper = ELBus.node(node.getData().getId());
        setId(wrapper, node);
        setTag(wrapper, node);
        setData(wrapper, node);
        setMaxWaitSeconds(wrapper, node);
        setRetry(wrapper, node);
        return wrapper;
    }


}
