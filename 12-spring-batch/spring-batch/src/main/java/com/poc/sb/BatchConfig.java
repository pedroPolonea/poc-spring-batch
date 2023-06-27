package com.poc.sb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job jobOddEven(
            JobRepository jobRepository,
            @Qualifier("oddEvenStep") Step step
    ){
        return new JobBuilder("ImprimirParImparJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step oddEvenStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            IteratorItemReader<Integer> getData
    ) throws Exception {
        log.info("I=Iniciando step oddEvenStep");
        return new StepBuilder("parImparStep", jobRepository)
                .allowStartIfComplete(true)
                .<Integer, String>chunk(10, transactionManager)
                .reader(getData)
                .processor(processorData())
                .writer(printData())
                .build();
    }

    @Bean
    public IteratorItemReader<Integer> getData() {
        List intList = new ArrayList<Integer>();
        int limit = new Random().nextInt(100);
        for (int i = 0; i < limit; i++) {
            intList.add(i);
        }
        return new IteratorItemReader<Integer>(intList);
    }

    @Bean
    public ItemProcessor<? super Integer,? extends String> processorData() {
        return item -> item % 2 == 0 ? String.format("Item %s é par", item): String.format("Item %s é impar", item);
    }

    @Bean
    public ItemWriter<? super String> printData() {
        return items -> items.forEach(s -> log.info(s));
    }

}
