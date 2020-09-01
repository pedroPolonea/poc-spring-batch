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
public class DelimitedFileReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<Client> delimitedFile(
            @Value("#{jobParameters['fileName']}") Resource fileClient){

        log.info("delimitedFile, I=Begin, fileClient={}", fileClient);
        return new FlatFileItemReaderBuilder<Client>()
                .name("fixedWidthFile")
                .resource(fileClient)
                .delimited()
                .names("name", "lastName", "age", "email")
                .targetType(Client.class)
                .build();

    }
}
