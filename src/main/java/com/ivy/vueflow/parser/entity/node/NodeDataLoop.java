package com.ivy.vueflow.parser.entity.node;

import lombok.Data;

@Data
public class NodeDataLoop {

    private Boolean parallel;//是否开启异步循环

    private Integer loopNumber;//for循环次数

}
