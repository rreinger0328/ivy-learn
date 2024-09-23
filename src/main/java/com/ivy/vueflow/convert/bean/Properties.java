package com.ivy.vueflow.convert.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@ApiModel(description ="elJson结构对象的属性")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Properties {

//    @ApiModelProperty(value = "id标签")
    private String id;

//    @ApiModelProperty(value = "tag标签")
    private String tag;

}

