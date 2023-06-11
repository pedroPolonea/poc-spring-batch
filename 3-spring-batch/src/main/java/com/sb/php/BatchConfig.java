package com.sb.php;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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
                .listener(jobListener())
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
        List intList = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            intList.add(i);
        }
        return new IteratorItemReader<Integer>(intList);
    }

    private ItemProcessor<? super Integer,? extends String> processorData() {
        return item -> item % 2 == 0 ? String.format("Item %s é par", item): String.format("Item %s é impar", item);
    }

    private ItemWriter<? super String> printData() {
        return items -> items.forEach(s -> log.info(s));
    }

    @Bean
    public JobCustomerExecutionListener jobListener() {
        return new JobCustomerExecutionListener();
    }
}