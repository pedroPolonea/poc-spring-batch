package com.poc.sb.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ParImparConfigProcessor implements ItemProcessor<Integer, String> {

    @Override
    public String process(final Integer item) throws Exception {
        log.info("process item={}", item);
        Thread.sleep(1000);
        return ((item % 2) == 0) ? String.format("Item %s é par", item): String.format("Item %s é impar", item);
    }
}
