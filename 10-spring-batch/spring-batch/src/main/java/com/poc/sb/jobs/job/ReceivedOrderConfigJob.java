package com.poc.sb.jobs.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
public class ReceivedOrderConfigJob {

    @Bean
    public Job receivedOrderJob(JobRepository jobRepository, @Qualifier("receivedOrderStep") Step step){
        log.info("I=Iniciando job receivedOrder");
        return new JobBuilder("receivedOrderJob",  jobRepository)
                .start(step)
                .build();
    }
}
