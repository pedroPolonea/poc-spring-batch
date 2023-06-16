package com.poc.sb.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ParImparConfigWrite implements ItemWriter<String> {
    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        log.info("write={}", chunk);
        chunk.forEach(log::info);
    }
}
