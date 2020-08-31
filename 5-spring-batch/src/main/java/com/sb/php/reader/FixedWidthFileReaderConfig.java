package com.sb.php.reader;

import com.sb.php.domain.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Slf4j
@Configuration
public class FixedWidthFileReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<Client> fixedWidthFile(
            @Value("#{jobParameters['fileName']}") Resource fileClient){

        log.info("fixedWidthFile, I=Begin, fileClient={}", fileClient);
        return new FlatFileItemReaderBuilder<Client>()
                .name("fixedWidthFile")
                .resource(fileClient)
                .fixedLength()
                .columns(new Range(1,10), new Range(11,20), new Range(21,23), new Range(24, 43))
                .names("name", "lastName", "age", "email")
                .targetType(Client.class)
                .build();

    }
}
