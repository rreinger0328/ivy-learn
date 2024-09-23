package com.ivy.vueflow.convert.parser.generator;

import com.ivy.vueflow.convert.bean.CmpProperty;
import com.ivy.vueflow.convert.bean.ELInfo;
import com.ivy.vueflow.convert.parser.base.ExpressParser;
import com.ivy.vueflow.convert.parser.selector.ParserSelector;
import com.ql.util.express.DefaultContext;
import com.yomahub.liteflow.common.ChainConstant;
import com.yomahub.liteflow.flow.FlowBus;
import com.yomahub.liteflow.flow.element.Condition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.yomahub.liteflow.builder.el.LiteFlowChainELBuilder.EXPRESS_RUNNER;

@Slf4j
@Component
public class ExpressGenerator {

    public CmpProperty generateJsonEL(ELInfo elInfo) {
        CmpProperty cmpProperty = new CmpProperty();

        try {
            DefaultContext<String, Object> context = new DefaultContext<>();

            // 这里一定要先放chain，再放node，因为node优先于chain，所以当重名时，node会覆盖掉chain
            // 往上下文里放入所有的chain，是的el表达式可以直接引用到chain
            FlowBus.getChainMap().values().forEach(chain -> context.put(chain.getChainId(), chain));

            // 往上下文里放入所有的node，使得el表达式可以直接引用到nodeId
            FlowBus.getNodeMap().keySet().forEach(nodeId -> context.put(nodeId, FlowBus.getNode(nodeId)));

            // 放入当前主chain的ID
            assert elInfo != null;
            context.put(ChainConstant.CURR_CHAIN_ID, elInfo.getChainId());

            List<String> errorList = new ArrayList<>();

            // promotionChain: THEN(fullCutCmp, fullDiscountCmp, rushBuyCmp);
            String elStr = elInfo.getElStr();
            System.out.println(elStr);
            Condition condition = (Condition) EXPRESS_RUNNER.execute(elStr, context, errorList, true, true);

            // 设置最 外层 内层, 其实每一层都是这样的
            // 1.id, condition是没有组件编码的,只有Node的时候才有
            // 2.type格式: THEN,SWITCH,IF,WHEN,FOR,WHILE,CATCH
            // 3.properties: id, tag 只有condition才有的属性, Node没有
            // 4.condition, 根据类型来区分, 比如:THEN、WHEN就没有条件
            // 5.children

            cmpProperty = builderJsonEL(condition);

        } catch (Exception ex) {
            log.error("-----", ex);
        }

        return cmpProperty;
    }

    private CmpProperty builderJsonEL(Condition condition) {
        ExpressParser parser = ParserSelector.getParser(condition);
        // id, type, property
        CmpProperty cmpProperty = parser.builderVO(condition);
        // conditionList
        cmpProperty.setCondition(parser.builderCondition(condition));
        // childList
        cmpProperty.setChildren(parser.builderChildren(condition));
        return cmpProperty;
    }

}
