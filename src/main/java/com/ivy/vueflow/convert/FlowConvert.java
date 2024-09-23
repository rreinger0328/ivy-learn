package com.ivy.vueflow.convert;

import com.yomahub.liteflow.builder.el.ELWrapper;

public interface FlowConvert {

    ELWrapper el2ELWrapper(String el);

    String el2Json(String el);

    String json2EL(String json);

}
