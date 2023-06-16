package com.poc.sb.resouce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JobResource {

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @GetMapping("/run")
    public ResponseEntity<?> runJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        log.info("runJob");
        jobLauncher.run(
                job,
                new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis(), true)
                    .addLong("endpoint", 1L)
                    .toJobParameters()
        );
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/run2")
    public ResponseEntity<?> runJob2() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        log.info("runJob");
        jobLauncher.run(
                job,
                new JobParametersBuilder()
                        .addLong("timestamp", System.currentTimeMillis(), true)
                        .addLong("endpoint", 2L)
                        .toJobParameters()
        );
        return ResponseEntity.ok()
                .build();
    }
}
