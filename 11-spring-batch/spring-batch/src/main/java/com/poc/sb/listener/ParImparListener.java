package com.poc.sb.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ParImparListener implements JobExecutionListener {

    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    private JobOperator jobOperator;

    @SneakyThrows
    @Override
    public void beforeJob(final JobExecution jobExecution) {
        if (jobExplorer.findRunningJobExecutions(jobExecution.getJobInstance().getJobName()).size() > 1) {
            jobOperator.stop(jobExecution.getId());
            //jobOperator.abandon(jobExecution.getId());
        }
    }
}
