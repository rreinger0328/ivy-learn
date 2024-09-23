package com.ivy.vueflow.convert;

import com.ivy.vueflow.builder.FlowBuilder;
import com.ivy.vueflow.builder.VueFlowBuilder;
import com.ivy.vueflow.convert.bean.CmpProperty;
import com.ivy.vueflow.convert.bean.ELInfo;
import com.ivy.vueflow.convert.parser.generator.ExpressGenerator;
import com.yomahub.liteflow.builder.el.ELWrapper;

import java.util.List;

public class VueFlowConvert implements FlowConvert {


    @Override
    public ELWrapper el2ELWrapper(String el) {
        return null;
    }

    @Override
    public String el2Json(String el) {
        ExpressGenerator expressGenerator = new ExpressGenerator();
        ELInfo elInfo = new ELInfo("chain", el);
        CmpProperty property = expressGenerator.generateJsonEL(elInfo);
        FlowBuilder builder = new VueFlowBuilder();
        builder.defaultData();
        build(builder,property);
        return builder.format(true).build();
    }

    private void build(FlowBuilder builder,CmpProperty property){
        if("THEN".equals(property.getType())){
            List<CmpProperty> children = property.getChildren();
            for (int i=0;i<children.size();i++){
                CmpProperty child = children.get(i);
                VueFlowBuilder.buildNode(child);
                if("NodeComponent".equals(child.getType())){
                    builder.addNode(child.getNode());
                }
                if(i > 0){
                    builder.addEdge(children.get(i-1).getNode(),children.get(i).getNode());
                }
            }
        }
    }

    @Override
    public String json2EL(String json) {
        return null;
    }


}
