package com.ivy.vueflow.parser.nodes;

import cn.hutool.core.util.StrUtil;
import com.ivy.vueflow.parser.bus.ELBusCatch;
import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.entity.node.NodeDataThen;
import com.ivy.vueflow.parser.entity.node.NodeDataWhen;
import com.yomahub.liteflow.builder.el.CatchELWrapper;
import com.yomahub.liteflow.builder.el.ELWrapper;
import com.yomahub.liteflow.builder.el.ThenELWrapper;
import com.yomahub.liteflow.builder.el.WhenELWrapper;

public class BaseNode {

    public static CatchELWrapper catchProcess(ELWrapper elWrapper, Node node){
        CatchELWrapper catchELWrapper = null;
        if(node != null){
            if(elWrapper instanceof ThenELWrapper && "then".equalsIgnoreCase(node.getType())){
                NodeDataThen nodeDataThen = node.getData().getNodeDataThen();
                if(nodeDataThen.getIsCatch() != null && nodeDataThen.getIsCatch()){
                    catchELWrapper = ELBusCatch.catchException(elWrapper);
                    if(StrUtil.isNotBlank(nodeDataThen.getCatchDo())){
                        catchELWrapper.doOpt(nodeDataThen.getCatchDo());
                    }
                }
            }else if(elWrapper instanceof WhenELWrapper && "when".equalsIgnoreCase(node.getType())){
                NodeDataWhen nodeDataWhen = node.getData().getNodeDataWhen();
                if(nodeDataWhen.getIsCatch() != null && nodeDataWhen.getIsCatch()){
                    catchELWrapper = ELBusCatch.catchException(elWrapper);
                    if(StrUtil.isNotBlank(nodeDataWhen.getCatchDo())){
                        catchELWrapper.doOpt(nodeDataWhen.getCatchDo());
                    }
                }
            }
        }
        return catchELWrapper;
    }

}
