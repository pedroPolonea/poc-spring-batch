package com.poc.sb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class Scheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("jobOddEven")
    private Job job;

    @Scheduled(cron = "0/15 * * * * ?")
    public void jobOddEvenScheduler() throws Exception{
        log.info("M=jobOddEvenScheduler, inicio");

        jobLauncher.run(job,
                new JobParametersBuilder()
                .addLong("run.id", System.nanoTime(), true)
                .toJobParameters()
        );

       // log.info("M=jobOddEvenScheduler, status={}", execution.getStatus());
    }
}
