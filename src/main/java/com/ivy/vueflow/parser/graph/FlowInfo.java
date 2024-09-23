package com.ivy.vueflow.parser.graph;

import com.ivy.vueflow.parser.entity.node.Node;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlowInfo {

    private Node node;

    private Boolean isBreak;
    private Boolean isContinue;

}
