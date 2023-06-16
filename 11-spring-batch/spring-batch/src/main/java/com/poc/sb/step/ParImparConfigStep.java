package com.poc.sb.step;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class ParImparConfigStep {

    @Bean
    public Step parImparStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<Integer> reader,
            ItemProcessor<Integer, String> processor,
            ItemWriter<String> writer
    ) throws Exception {
        log.info("I=Iniciando step parImparStep");
        return new StepBuilder("parImparStep", jobRepository)
                .<Integer, String>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
