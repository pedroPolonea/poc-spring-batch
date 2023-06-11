package com.sb.php;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobCustomerExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("jobParameters={}", jobExecution.getJobParameters());
        jobExecution.getJobInstance().setId(2L);
        log.info("jobParameters={}", jobExecution.getJobParameters());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Sucesso na execucao, jobExecution={}", jobExecution);
        } else {
            log.error("Falha na execucao, jobExecution={}", jobExecution);
        }
    }
}
