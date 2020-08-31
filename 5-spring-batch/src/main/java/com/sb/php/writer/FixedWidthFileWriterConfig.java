package com.sb.php.writer;

import com.sb.php.domain.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FixedWidthFileWriterConfig {

    @Bean
    public ItemWriter<Client> writerFixedWidthFile(){
        return items -> items.forEach(client -> log.info("Client={}",client));
    }
}
