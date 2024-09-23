package com.ivy.vueflow.parser.entity.node;

import lombok.Data;

import java.util.Map;

@Data
public class NodeDataContext {

    //别名
    private String asName;
    //bean
    private String fullClassName;
    //class
    private Class<?> clazz;
    //参数
    private Map<String,Object> params;

}
