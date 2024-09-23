package com.ivy.vueflow.convert.parser.el;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ivy.vueflow.convert.bean.CmpProperty;
import com.ivy.vueflow.convert.bean.Properties;
import com.ivy.vueflow.convert.enums.ExpressParserEnum;
import com.ivy.vueflow.convert.parser.base.AbstractExpressParser;
import com.yomahub.liteflow.enums.ConditionTypeEnum;
import com.yomahub.liteflow.flow.element.Condition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 并行解析
 *
 * @author <a href="mailto:dogsong99@163.com">dosong</a>
 * @since 2024/4/23
 */
@Component
public class WhenConditionParser extends AbstractExpressParser {

    @Override
    public ConditionTypeEnum parserType() {
        return ConditionTypeEnum.TYPE_WHEN;
    }

    @Override
    public CmpProperty builderCondition(Condition condition) {
        return new CmpProperty();
    }

    @Override
    public List<CmpProperty> builderChildren(Condition condition) {
        List<CmpProperty> children = new ArrayList<>();
        builderChildList(condition.getExecutableList(), children);
        return children;
    }

    @Override
    public String generateELMethod(CmpProperty jsonEl) {
        return elWhenMethod;
    }

    @Override
    public String generateCondition(CmpProperty jsonEl, String elExpress) {
        // 填充EL条件, WHEN没有条件, WHEN({})
        return elExpress;
    }

    @Override
    public String generateCmp(CmpProperty jsonEl, String elExpress) {
        if (CollectionUtil.isNotEmpty(jsonEl.getChildren())) {
            String nodeComponentId = "";
            for (CmpProperty child : jsonEl.getChildren()) {
                nodeComponentId = generateNodeComponent(child, nodeComponentId);
                // 这里会多拼接一个逗号
                nodeComponentId = StrUtil.appendIfMissing(nodeComponentId, elSeparate);
            }
            // 去除多的逗号
            nodeComponentId = StringUtils.substringBeforeLast(nodeComponentId, elSeparate);
            // 填充EL组件, THEN({}) -> THEN(a, b, c)
            elExpress = StrUtil.format(elExpress, nodeComponentId);
        }
        return elExpress;
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
    public ExpressParserEnum getExpressType(Condition condition) {
        return ExpressParserEnum.WHEN;
    }
}
