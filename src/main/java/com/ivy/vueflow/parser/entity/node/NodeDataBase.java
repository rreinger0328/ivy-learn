package com.ivy.vueflow.parser.entity.node;

import lombok.Data;

@Data
public class NodeDataBase {

    private String id;//表达式id
    private String tag;//组件标签
    private String dataName;//组件参数名称
    private String data;//组件参数值
    private Integer maxWaitSeconds;//最大等待时间
    private Integer retryCount;//重试次数
    private String[] retryExceptions;//指定异常的重试

    private String language;//脚本语言
    private String code;//脚本代码\动态类代码
    private String clazz;//类路径
    private Integer classType;//构建节点【0：普通类，1：BEAN，2：动态类，3：脚本】

}
