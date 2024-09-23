package com.ivy.vueflow.parser.entity.node;

import com.ivy.vueflow.parser.entity.style.NodeStyle;
import lombok.Data;

@Data
public class NodeData {

    private String id;//组件ID
    private String name;//组件别名
    private String type;//组件类型
    private String mode;//显示模式：default,simple

    private NodeDataBase nodeDataBase;//基本属性
    private NodeDataSwitch nodeDataSwitch;//选择属性
    private NodeDataIf nodeDataIf;//条件属性
    private NodeDataLoop nodeDataLoop;//循环属性
    private NodeDataWhen nodeDataWhen;//并行属性
    private NodeDataThen nodeDataThen;//串行属性
    private NodeDataChain nodeDataChain;//链路属性
    private NodeDataSubFlow nodeDataSubflow;//子流程属性
    private NodeDataSubVar nodeDataSubvar;//子变量属性
    private NodeDataRouter nodeDataRouter;//路由属性
    private NodeDataContext nodeDataContext;//上下文属性

    private NodeStyle style;

}
