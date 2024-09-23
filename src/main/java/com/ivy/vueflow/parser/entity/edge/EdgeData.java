package com.ivy.vueflow.parser.entity.edge;

import com.ivy.vueflow.parser.enums.IvyEnums;
import lombok.Data;

@Data
public class EdgeData {

    // 路径类型 IvyEnums.PATH_ENUM
    private String type;

    private String id;//表达式ID
    private String tag;//表达式标签

    public boolean isCommonPath(){
        return IvyEnums.PATH_ENUM.common_path.value().equals(type);
    }

    //是否特殊路径
    public boolean isSpecialPath(){
        return !isCommonPath();
    }

    public boolean isPath(IvyEnums.PATH_ENUM pathEnum){
        return pathEnum.value().equals(type);
    }

}
