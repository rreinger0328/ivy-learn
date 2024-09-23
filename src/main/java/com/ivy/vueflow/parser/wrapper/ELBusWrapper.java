package com.ivy.vueflow.parser.wrapper;

import cn.hutool.core.util.StrUtil;
import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.entity.node.NodeData;
import com.ivy.vueflow.parser.entity.node.NodeDataChain;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.yomahub.liteflow.builder.el.ELBus;

public class ELBusWrapper extends ELBus {

    public static ChainELWrapper chain(String chainId) {
        return new ChainELWrapper(chainId);
    }

    public static ChainELWrapper chain(Node node) throws LiteFlowELException {
        NodeData data = node.getData();
        if(data != null){
            NodeDataChain nodeDataChain = data.getNodeDataChain();
            if(nodeDataChain != null){
                String chainId = nodeDataChain.getChainId();
                if(StrUtil.isNotBlank(chainId)){
                    return ELBusWrapper.chain(chainId);
                }
            }
        }
        throw new LiteFlowELException("链路组件未设置chainId！");
    }

}
