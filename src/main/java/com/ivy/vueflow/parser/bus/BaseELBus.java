package com.ivy.vueflow.parser.bus;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ivy.vueflow.parser.entity.node.Node;
import com.ivy.vueflow.parser.entity.node.NodeDataBase;
import com.ivy.vueflow.parser.entity.node.NodeDataLoop;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.ivy.vueflow.parser.graph.Graph;
import com.ivy.vueflow.parser.wrapper.ELBusWrapper;
import com.yomahub.liteflow.builder.el.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class BaseELBus {

    public static void eLWrapperConvert(ELWrapper eLWrapper, Node node) throws LiteFlowELException {
        if(eLWrapper instanceof ThenELWrapper){
            eLWrapperConvert(eLWrapper, buildNode(node), null);
        }else if(eLWrapper instanceof WhenELWrapper){
            eLWrapperConvert(eLWrapper, buildNode(node), null);
        }
    }

    public static void eLWrapperConvert(ELWrapper eLWrapper, ELWrapper eLWrapper2, CatchELWrapper catchELWrapper) throws LiteFlowELException {
        if(catchELWrapper != null){
            if(eLWrapper instanceof ThenELWrapper){
                ((ThenELWrapper) eLWrapper).then(catchELWrapper);
            }else if(eLWrapper instanceof WhenELWrapper){
                ((WhenELWrapper) eLWrapper).when(catchELWrapper);
            }
        }else{
            if(eLWrapper2 == null){
                throw new LiteFlowELException("EL表达式不能为空！");
            }
            if(eLWrapper instanceof ThenELWrapper){
                ((ThenELWrapper) eLWrapper).then(eLWrapper2);
            }else if(eLWrapper instanceof WhenELWrapper){
                ((WhenELWrapper) eLWrapper).when(eLWrapper2);
            }
        }
    }

    public static ELWrapper buildNode(Node node) throws LiteFlowELException {
        String type = node.getType();
        switch (type){
            case "common": return ELBusNode.node(node);
            case "switch": return ELBusSwitch.node(node);
            case "if": return ELBusIf.node(node);
            case "chain": return ELBusWrapper.chain(node);
        }
        return ELBusThen.then();
    }

    public static ELWrapper buildELWrapper(Node node, Graph graph) throws LiteFlowELException {
        boolean hasNextNode = graph.hasNextNode(node);
        if(hasNextNode || (graph.getNodes().size() == 1 && "common".equals(node.getType()))){
            return ELBusThen.then();
        }
        return buildNode(node);
    }

    protected static void setDoOpt(ELWrapper wrapper, Node node){
//        Object doOpt = node.getCmpDoOptEL() != null ? node.getCmpDoOptEL() : node.getCmpDoOpt();
//        setDoOpt(wrapper, doOpt);
    }

    protected static void setDoOpt(ELWrapper wrapper, Object doOpt){
        if(wrapper instanceof CatchELWrapper){
            CatchELWrapper elWrapper = (CatchELWrapper) wrapper;
            elWrapper.doOpt(doOpt);
        }
    }

    protected static void setId(ELWrapper wrapper, Node node){
        NodeDataBase nodeDataBase = node.getData().getNodeDataBase();
        if(nodeDataBase != null && StrUtil.isNotEmpty(nodeDataBase.getId())){
            setId(wrapper, nodeDataBase.getId());
        }
    }

    protected static void setId(ELWrapper wrapper, String id){
        if(StrUtil.isNotBlank(id)){
            if(wrapper instanceof NodeELWrapper){
                ((NodeELWrapper) wrapper).id(id);
            }else if(wrapper instanceof AndELWrapper){
                ((AndELWrapper) wrapper).id(id);
            }else if(wrapper instanceof CatchELWrapper){
                ((CatchELWrapper) wrapper).id(id);
            }else if(wrapper instanceof FinallyELWrapper){
                ((FinallyELWrapper) wrapper).id(id);
            }else if(wrapper instanceof PreELWrapper){
                ((PreELWrapper) wrapper).id(id);
            }else if(wrapper instanceof NotELWrapper){
                ((NotELWrapper) wrapper).id(id);
            }else if(wrapper instanceof OrELWrapper){
                ((OrELWrapper) wrapper).id(id);
            }else if(wrapper instanceof WhenELWrapper){
                ((WhenELWrapper) wrapper).id(id);
            }else if(wrapper instanceof ThenELWrapper){
                ((ThenELWrapper) wrapper).id(id);
            }else if(wrapper instanceof LoopELWrapper){
                ((LoopELWrapper) wrapper).id(id);
            }else if(wrapper instanceof IfELWrapper){
                ((IfELWrapper) wrapper).id(id);
            }else if(wrapper instanceof ParELWrapper){
                ((ParELWrapper) wrapper).id(id);
            }else if(wrapper instanceof SerELWrapper){
                ((SerELWrapper) wrapper).id(id);
            }else if(wrapper instanceof SwitchELWrapper){
                ((SwitchELWrapper) wrapper).id(id);
            }
        }
    }

    protected static void setTag(ELWrapper wrapper, Node node){
        NodeDataBase nodeDataBase = node.getData().getNodeDataBase();
        if(nodeDataBase != null && StrUtil.isNotEmpty(nodeDataBase.getTag())){
            setTag(wrapper, nodeDataBase.getTag());
        }
    }

    protected static void setTag(ELWrapper wrapper, String tag){
        if(StrUtil.isNotBlank(tag)) {
            if (wrapper instanceof NodeELWrapper) {
                ((NodeELWrapper) wrapper).tag(tag);
            } else if (wrapper instanceof AndELWrapper) {
                ((AndELWrapper) wrapper).tag(tag);
            } else if (wrapper instanceof CatchELWrapper) {
                ((CatchELWrapper) wrapper).tag(tag);
            } else if (wrapper instanceof FinallyELWrapper) {
                ((FinallyELWrapper) wrapper).tag(tag);
            } else if (wrapper instanceof PreELWrapper) {
                ((PreELWrapper) wrapper).tag(tag);
            } else if (wrapper instanceof NotELWrapper) {
                ((NotELWrapper) wrapper).tag(tag);
            } else if (wrapper instanceof OrELWrapper) {
                ((OrELWrapper) wrapper).tag(tag);
            } else if (wrapper instanceof WhenELWrapper) {
                ((WhenELWrapper) wrapper).tag(tag);
            } else if (wrapper instanceof ThenELWrapper) {
                ((ThenELWrapper) wrapper).tag(tag);
            } else if (wrapper instanceof LoopELWrapper) {
                ((LoopELWrapper) wrapper).tag(tag);
            }else if(wrapper instanceof IfELWrapper){
                ((IfELWrapper) wrapper).tag(tag);
            }else if(wrapper instanceof ParELWrapper){
                ((ParELWrapper) wrapper).tag(tag);
            }else if(wrapper instanceof SerELWrapper){
                ((SerELWrapper) wrapper).tag(tag);
            }else if(wrapper instanceof SwitchELWrapper){
                ((SwitchELWrapper) wrapper).tag(tag);
            }
        }
    }

    protected static void setParallel(ELWrapper wrapper, Node node){
        NodeDataLoop nodeDataLoop = node.getData().getNodeDataLoop();
        if(nodeDataLoop != null && nodeDataLoop.getParallel() != null) {
            setParallel(wrapper, nodeDataLoop.getParallel());
        }
    }

    protected static void setParallel(ELWrapper wrapper, Boolean parallel){
        if(wrapper instanceof LoopELWrapper){
            ((LoopELWrapper) wrapper).parallel(parallel);
        }
    }

    protected static void setRetry(ELWrapper wrapper, Node node){
        NodeDataBase nodeDataBase = node.getData().getNodeDataBase();
        if(nodeDataBase != null && nodeDataBase.getRetryCount() != null){
            if(ArrayUtil.isNotEmpty(nodeDataBase.getRetryExceptions())){
                setRetry(wrapper, nodeDataBase.getRetryCount(), nodeDataBase.getRetryExceptions());
            }else{
                setRetry(wrapper, nodeDataBase.getRetryCount());
            }
        }
    }

    protected static void setRetry(ELWrapper wrapper, Integer retryCount){
        if(wrapper instanceof NodeELWrapper){
            ((NodeELWrapper) wrapper).retry(retryCount);
        }else if(wrapper instanceof CatchELWrapper){
            ((CatchELWrapper) wrapper).retry(retryCount);
        }else if(wrapper instanceof PreELWrapper){
            ((PreELWrapper) wrapper).retry(retryCount);
        }else if(wrapper instanceof WhenELWrapper){
            ((WhenELWrapper) wrapper).retry(retryCount);
        }else if(wrapper instanceof ThenELWrapper){
            ((ThenELWrapper) wrapper).retry(retryCount);
        }else if(wrapper instanceof LoopELWrapper){
            ((LoopELWrapper) wrapper).retry(retryCount);
        }else if(wrapper instanceof FinallyELWrapper){
            ((FinallyELWrapper) wrapper).retry(retryCount);
        }else if(wrapper instanceof IfELWrapper){
            ((IfELWrapper) wrapper).retry(retryCount);
        }else if(wrapper instanceof ParELWrapper){
            ((ParELWrapper) wrapper).retry(retryCount);
        }else if(wrapper instanceof SerELWrapper){
            ((SerELWrapper) wrapper).retry(retryCount);
        }else if(wrapper instanceof SwitchELWrapper){
            ((SwitchELWrapper) wrapper).retry(retryCount);
        }
    }

    protected static void setRetry(ELWrapper wrapper, Integer retryCount, String[] retryExceptions){
        if(wrapper instanceof NodeELWrapper){
            ((NodeELWrapper) wrapper).retry(retryCount, retryExceptions);
        }else if(wrapper instanceof CatchELWrapper){
            ((CatchELWrapper) wrapper).retry(retryCount, retryExceptions);
        }else if(wrapper instanceof PreELWrapper){
            ((PreELWrapper) wrapper).retry(retryCount, retryExceptions);
        }else if(wrapper instanceof WhenELWrapper){
            ((WhenELWrapper) wrapper).retry(retryCount, retryExceptions);
        }else if(wrapper instanceof ThenELWrapper){
            ((ThenELWrapper) wrapper).retry(retryCount, retryExceptions);
        }else if(wrapper instanceof LoopELWrapper){
            ((LoopELWrapper) wrapper).retry(retryCount, retryExceptions);
        }else if(wrapper instanceof FinallyELWrapper){
            ((FinallyELWrapper) wrapper).retry(retryCount, retryExceptions);
        }else if(wrapper instanceof IfELWrapper){
            ((IfELWrapper) wrapper).retry(retryCount, retryExceptions);
        }else if(wrapper instanceof ParELWrapper){
            ((ParELWrapper) wrapper).retry(retryCount, retryExceptions);
        }else if(wrapper instanceof SerELWrapper){
            ((SerELWrapper) wrapper).retry(retryCount, retryExceptions);
        }else if(wrapper instanceof SwitchELWrapper){
            ((SwitchELWrapper) wrapper).retry(retryCount, retryExceptions);
        }
    }

    protected static void setMaxWaitSeconds(ELWrapper wrapper, Node node){
        NodeDataBase nodeDataBase = node.getData().getNodeDataBase();
        if(nodeDataBase != null && nodeDataBase.getMaxWaitSeconds() != null){
            setMaxWaitSeconds(wrapper, nodeDataBase.getMaxWaitSeconds());
        }
    }

    protected static void setMaxWaitSeconds(ELWrapper wrapper, Integer maxWaitSeconds){
        if(wrapper instanceof NodeELWrapper){
            ((NodeELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof AndELWrapper){
//            ((AndELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof CatchELWrapper){
            ((CatchELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof PreELWrapper){
            ((PreELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof NotELWrapper){
//            ((NotELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof OrELWrapper){
//            ((OrELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof WhenELWrapper){
            ((WhenELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof ThenELWrapper){
            ((ThenELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof LoopELWrapper){
            ((LoopELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof IfELWrapper){
            ((IfELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof ParELWrapper){
            ((ParELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof SerELWrapper){
            ((SerELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }else if(wrapper instanceof SwitchELWrapper){
            ((SwitchELWrapper) wrapper).maxWaitSeconds(maxWaitSeconds);
        }
    }

    protected static void setData(NodeELWrapper wrapper, Node node) throws LiteFlowELException {
        NodeDataBase nodeDataBase = node.getData().getNodeDataBase();
        if(nodeDataBase != null){
            boolean dataNameIsBlank = StrUtil.isBlank(nodeDataBase.getDataName());
            boolean dataIsBlank = StrUtil.isBlank(nodeDataBase.getData());
            if(dataNameIsBlank && !dataIsBlank){
                //nodeDataBase.setDataName("P_"+UUID.fastUUID().toString(true).substring(0,5));
                throw new LiteFlowELException("组件参数名称未设置！");
            }else if(!dataNameIsBlank && dataIsBlank){
                throw new LiteFlowELException("组件参数数据未设置！");
            }else if(!dataNameIsBlank){
                setData(wrapper, nodeDataBase.getDataName(), nodeDataBase.getData());
            }
        }
    }

    protected static void setData(NodeELWrapper wrapper, String dataName, String jsonData) throws LiteFlowELException {
        if(StrUtil.isNotEmpty(jsonData)){
            jsonData = jsonData.trim();
            try {
                if (jsonData.startsWith("{") && jsonData.endsWith("}")) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Map<String, Object>>(){}.getType(); // 获取Map的Type
                    Object obj = gson.fromJson(jsonData, type);
                } else if (jsonData.startsWith("[") && jsonData.endsWith("]")) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Map<String, Object>>>(){}.getType(); // 获取List的Type
                    Object obj = gson.fromJson(jsonData, type);
                }
            } catch (Exception e) {
                System.err.println("ELBusNode: Invalid JSON format.");
                System.err.println("dataName: " + dataName);
                System.err.println("jsonData: " + jsonData);
                throw new LiteFlowELException("ELBusNode: Invalid JSON format."+System.lineSeparator()+"dataName: " + dataName+System.lineSeparator()+"jsonData: " + jsonData);
            }
            wrapper.data(dataName, jsonData);
        }
    }

    protected static void setData(NodeELWrapper wrapper, String dataName, Object data){
        if(data != null){
            wrapper.data(dataName, data);
        }
    }

    protected static void setData(NodeELWrapper wrapper, String dataName, Map<String, Object> jsonMap){
        if(CollUtil.isNotEmpty(jsonMap)){
            wrapper.data(dataName, jsonMap);
        }
    }
}
