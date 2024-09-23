package com.ivy.vueflow.parser.entity.node;

import lombok.Data;

@Data
public class NodeDataThen {

    private String id;//表达式ID
    private String tag;//标签
    private Boolean isCatch;
    private String catchDo;

}
