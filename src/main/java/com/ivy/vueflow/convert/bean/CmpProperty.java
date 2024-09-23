package com.ivy.vueflow.convert.bean;

import com.ivy.vueflow.parser.entity.node.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@ApiModel(description ="elJson结构对象")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CmpProperty {

//    @ApiModelProperty(value = "id")
    private String id;

//    @ApiModelProperty(value = "类型")
    private String type;

//    @ApiModelProperty(value = "属性")
    private Properties properties;

//    @ApiModelProperty(value = "条件")
    private CmpProperty condition;

//    @ApiModelProperty(value = "子集")
    private List<CmpProperty> children;

    private Node node;
}
