package com.ivy.vueflow.parser.graph;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ivy.vueflow.parser.entity.node.NodeDataContext;
import com.yomahub.liteflow.builder.el.ELWrapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class GraphInfo {

    private List<NodeDataContext> contextList;

    //主流程
    private Graph mainGraph;
    private ELWrapper mainGraphELWrapper;
    private String mainELStr;

    //子流程
    private List<Graph> subFlowGraphs;
    private Map<String,ELWrapper> subFlowELWrapperMap;
    private Map<String,String> subFlowELMap;

    //子变量
    private List<Graph> subVarGraphs;
    private Map<String,ELWrapper> subVarELWrapperMap;
    private Map<String,String> subVarELMap;

    //路由
    private Graph routerGraph;
    private ELWrapper routerELWrapper;
    private String routerNamespace;
    private String routerELStr;

    private static final String rn = System.lineSeparator();

    @Override
    public String toString() {
        List<String> elList = new ArrayList<>();
        if(CollUtil.isNotEmpty(contextList)){
            elList.add("【上下文】");
            List<String> cList = new ArrayList<>();
            contextList.forEach(m->{
                cList.add("["+m.getAsName()+"] "+m.getFullClassName());
            });
            elList.add(String.join(rn, cList) + (StrUtil.isNotBlank(mainELStr) ? rn : ""));
        }
        if(routerGraph != null){
            elList.add("【路由】");
            elList.add("["+routerNamespace+"] "+routerELStr);
        }
        if(CollUtil.isNotEmpty(subFlowGraphs)){
            List<String> elSubFlowList = new ArrayList<>();
            elSubFlowList.add("【子流程】");
            subFlowELMap.entrySet().forEach(m->{
                elSubFlowList.add("["+m.getKey()+"] "+m.getValue());
            });
            elList.add(String.join(rn, elSubFlowList));
        }
        if(CollUtil.isNotEmpty(subVarGraphs)){
            List<String> elSubVarList = new ArrayList<>();
            subVarELMap.entrySet().forEach(m->{
                elSubVarList.add(m.getKey()+" = "+m.getValue());
            });
            elList.add(String.join(rn, elSubVarList));
        }
        if(routerGraph != null || CollUtil.isNotEmpty(subFlowGraphs)){
            elList.add("【主流程】");
        }
        elList.add(mainELStr);
        return String.join(rn, elList);
    }

}
