package com.costa.luiz.payments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job {} started at {}", jobExecution.getJobId(), jobExecution.getCreateTime());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (BatchStatus.COMPLETED == jobExecution.getStatus()) {
            log.info("Job {} finished at {} took {}", jobExecution.getJobId(), jobExecution.getEndTime(),
                    Duration.between(jobExecution.getCreateTime(), jobExecution.getEndTime()));

//            jdbcTemplate
//                    .query("SELECT * FROM payments", new DataClassRowMapper<>(Payment.class))
//                    .forEach(payments -> log.info("Found <{{}}> in the database.", payments));
        }
    }
}

//Job 1 finished at 2024-02-23T23:26:32.697781
//Job 1 started at 2024-02-23T23:26:32.697781