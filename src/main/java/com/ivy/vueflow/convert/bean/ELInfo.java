package com.ivy.vueflow.convert.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@ApiModel(description ="el结构对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ELInfo {

//    @ApiModelProperty(value = "链名称")
    private String chainId;

//    @ApiModelProperty(value = "EL表达式")
    private String elStr;

}
