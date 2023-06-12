package com.sb.php.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job firstJob(Step stepJob, Step stepJobListenerRabbit){
        log.info("JobConfig.firstJob, I=Begin ,stepName={}", stepJob.getName());
        return jobBuilderFactory
                .get("covid19Job")
                .incrementer(new RunIdIncrementer())
                .start(stepJob)
                .next(stepJobListenerRabbit)
                .build();

    }
}
