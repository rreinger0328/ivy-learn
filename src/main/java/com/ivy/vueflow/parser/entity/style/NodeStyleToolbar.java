package com.ivy.vueflow.parser.entity.style;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeStyleToolbar {

    private String position;
    private Boolean showIcon;
    private Integer offset;

}
