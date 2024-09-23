package com.ivy.vueflow.convert.parser.factory;

import cn.hutool.core.collection.CollUtil;
import com.ivy.vueflow.convert.parser.base.ExpressParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 解析器工厂
 *
 * @author <a href="mailto:dogsong99@163.com">dosong</a>
 * @since 2024/4/18
 */
@Slf4j
@Component
public class ExpressParserFactory {

    /**
     * 解析器容器
     */
    public static final Map<String, ExpressParser> PARSER_MAP = new ConcurrentHashMap<>();

    @Autowired(required = false)
    public void setParsers(List<ExpressParser> parsers) {
        if (CollUtil.isNotEmpty(parsers)) {
            parsers.forEach(this::register);
        }
    }

    public void register(ExpressParser parser) {
        if (parser.parserType() == null) {
            return;
        }
        Assert.notNull(parser, "ExpressParser parser must not be null");
        PARSER_MAP.put(parser.parserType().getType(), parser);
        log.info("ExpressParser[{}] has been found", parser.parserType());
    }


}
