package com.sb.php;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobParImpar(){
        return jobBuilderFactory
                .get("ImprimirParImparJob")
                .start(stepJob())
                .build();

    }

    private Step stepJob() {
        return stepBuilderFactory
                .get("ImprimirParImparStep")
                .<Integer, String>chunk(10)
                .reader(getData())
                .processor(processorData())
                .writer(printData())
                .build();
    }

    private IteratorItemReader<Integer> getData() {
        return new IteratorItemReader<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    }

    private ItemProcessor<? super Integer,? extends String> processorData() {
        return item -> item % 2 == 0 ? String.format("Item %s é par", item): String.format("Item %s é impar", item);
    }

    private ItemWriter<? super String> printData() {
        return items -> items.forEach(s -> log.info(s));
    }
}
