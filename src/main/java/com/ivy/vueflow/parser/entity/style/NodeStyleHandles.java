package com.ivy.vueflow.parser.entity.style;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeStyleHandles {

    private String id;
    private String position;
    private Double opacity;
    private String type;
    private String style;

}
