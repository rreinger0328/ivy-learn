package com.ivy.vueflow.parser.entity;

import lombok.Data;

@Data
public class FlowDataVo extends FlowData {

    private String requestId;
    private String nodeId;
    private String nodeType;

    private Integer loopNumber;

}
