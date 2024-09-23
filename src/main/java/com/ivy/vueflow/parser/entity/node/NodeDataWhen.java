package com.ivy.vueflow.parser.entity.node;

import lombok.Data;

@Data
public class NodeDataWhen {

    private String id;//表达式ID
    private String tag;//标签
    private Boolean ignoreError;
    private String ignoreType;
    private String mustId;
    private Boolean isCatch;
    private String catchDo;
    private String customThreadExecutor;

}
