package com.ivy.vueflow.convert.parser.el;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ivy.vueflow.convert.bean.CmpProperty;
import com.ivy.vueflow.convert.bean.Properties;
import com.ivy.vueflow.convert.enums.ExpressParserEnum;
import com.ivy.vueflow.convert.parser.base.AbstractLoopExpressParser;
import com.yomahub.liteflow.enums.ConditionTypeEnum;
import com.yomahub.liteflow.enums.NodeTypeEnum;
import com.yomahub.liteflow.flow.element.Condition;
import com.yomahub.liteflow.flow.element.Node;
import com.yomahub.liteflow.flow.element.condition.ForCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * 循环解析
 *
 * @author <a href="mailto:dogsong99@163.com">dosong</a>
 * @since 2024/4/23
 */
@Component
public class ForConditionParser extends AbstractLoopExpressParser {

    @Override
    public ConditionTypeEnum parserType() {
        return ConditionTypeEnum.TYPE_FOR;
    }

    @Override
    public CmpProperty builderCondition(Condition condition) {
        ForCondition forCondition = (ForCondition) condition;
        Node forNode = forCondition.getForNode();
        // Node 转 CmpPropertyVO
        return Optional.of(forNode).map(nodeMapper).orElse(new CmpProperty());
    }


    @Override
    public String generateELMethod(CmpProperty jsonEl) {
        return elForMethod;
    }

    @Override
    public String generateCondition(CmpProperty jsonEl, String elExpress) {
        if (Objects.isNull(jsonEl.getCondition())) {
            return elExpress;
        }
        CmpProperty condition = jsonEl.getCondition();
        return StrUtil.replaceFirst(elExpress, "{}", condition.getId());
    }

    @Override
    public String generateCmp(CmpProperty jsonEl, String elExpress) {
        if (CollectionUtil.isEmpty(jsonEl.getChildren())) {
            return elExpress;
        }
        // 获取 DO({}) 内部的组件
        CmpProperty doExpressVO = nonBreakMapper(jsonEl.getChildren());
        // 生成 DO({}) 内部的表达式 -> THEN(b,c)
        String doEL = generateDoEL(doExpressVO);
        // 填充EL组件, FOR(a).DO({}) -> FOR(a).DO(THEN(b,c))
        return StrUtil.format(elExpress, doEL);
    }

    @Override
    public String generateIdAndTag(CmpProperty jsonEl, String elExpress) {
        Properties properties = jsonEl.getProperties();
        if (Objects.isNull(properties)) {
            return elExpress;
        }

        // 该表达式的 id 或者 tag
        String expressIdAndTag = "";
        if (StringUtils.isNotEmpty(properties.getId())) {
            expressIdAndTag = StrUtil.format(elExpressId, properties.getId());
        }
        if (StringUtils.isNotEmpty(properties.getTag())) {
            expressIdAndTag = expressIdAndTag + StrUtil.format(elExpressTag, properties.getTag());
        }
        return StrUtil.appendIfMissing(elExpress, expressIdAndTag);
    }

    @Override
    public String generateBreak(CmpProperty jsonEl, String elExpress) {
        if (Objects.isNull(jsonEl.getChildren()) || jsonEl.getChildren().isEmpty()) {
            return elExpress;
        }

        CmpProperty breakVO = breakMapper(jsonEl.getChildren());
        if (Objects.isNull(breakVO)) {
            return elExpress;
        }

        CmpProperty breakNode = foundBreakNode(breakVO.getChildren());
        if (Objects.isNull(breakNode)) {
            return elExpress;
        }

        // 循环跳出 BREAK 语句处理
        if (StringUtils.equals(NodeTypeEnum.BOOLEAN.getMappingClazz().getSimpleName(), breakNode.getType())) {
            // .BREAK({}) -> .BREAK(d)
            String breakEL = StrUtil.format(elBreakMethod, breakNode.getId());
            // 补充 break 语句: FOR(a).DO(THEN(b,c)) -> FOR(a).DO(THEN(b,c)).BREAK(d)
            elExpress = elExpress + breakEL;
        }
        return elExpress;
    }

    @Override
    public ExpressParserEnum getExpressType(Condition condition) {
        return ExpressParserEnum.FOR;
    }
}
