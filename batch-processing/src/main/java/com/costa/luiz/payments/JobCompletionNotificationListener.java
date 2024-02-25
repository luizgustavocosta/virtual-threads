package com.costa.luiz.payments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.DataClassRowMapper;
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

            var countPayments = jdbcTemplate
                    .queryForObject("SELECT count(*) FROM payments", Integer.class);
            var countPaymentsSkipped = jdbcTemplate
                    .queryForObject("SELECT count(*) FROM payments WHERE status = 'SKIP'", Integer.class);
            var countPaymentsOk = jdbcTemplate
                    .queryForObject("SELECT count(*) FROM payments WHERE status = 'OK'", Integer.class);

            log.info("Were processed {} payments. {} OK and {} SKIP to next processing window ",
                    countPayments, countPaymentsOk, countPaymentsSkipped);

            jdbcTemplate
                    .query("SELECT * FROM payments", new DataClassRowMapper<>(Payment.class))
                    .forEach(payments -> log.debug("Found <{{}}> in the database.", payments));
        }
    }
}