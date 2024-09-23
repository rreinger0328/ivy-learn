package com.ivy.vueflow.parser.enums;


import com.ivy.vueflow.parser.enums.core.IDictItem;
import com.ivy.vueflow.parser.enums.core.StaticDictPool;

public interface IvyEnums {

    enum PATH_ENUM implements IDictItem {
        common_path("common","普通路径"),
        to_path("to","to(Switch路径)"),
        default_path("default","default(Switch路径)"),
        boolean_path("boolean","boolean(Boolean路径)"),
        true_path("true","true(If路径)"),
        false_path("false","false(If路径)"),
        do_path("do","do路径"),
        break_path("break","break路径"),
        ;

        PATH_ENUM(String value, String label){
            StaticDictPool.putDictItem(this,value,label);
        }

    }

}
