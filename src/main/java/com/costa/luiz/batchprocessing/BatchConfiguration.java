package com.costa.luiz.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {


    @Value("${payment.files.location}")
    private Resource[] files;

    @Bean
    public PaymentItemProcessor processor() {
        return new PaymentItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Payment> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Payment>()
                .sql("INSERT INTO payments (account, amount, status) VALUES (:account, :amount, :status)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public Job transferJob(JobRepository jobRepository,
                           Step paymentStep,
                           JobCompletionNotificationListener listener) {
        return new JobBuilder("transferJob", jobRepository)
                .listener(listener)
                .start(paymentStep)
                .build();
    }

    @Bean
    public MultiResourceItemReader<Payment> multiResourceItemReader() {
        return new MultiResourceItemReaderBuilder<Payment>()
                .name("Payments")
                .resources(files)
                .delegate(flatFileItemReader())
                .build();
    }

    public MultiResourceReaderThreadSafe<Payment> multiResourceReaderThreadSafe() {
        var safe = new MultiResourceReaderThreadSafe<>(multiResourceItemReader());
        safe.setResources(files);
        return safe;
    }

    @Bean
    public ResourceAwareItemReaderItemStream<Payment> flatFileItemReader() {
        return new FlatFileItemReaderBuilder<Payment>()
                .name("Payment reader")
                .saveState(Boolean.FALSE)
                .linesToSkip(1)
                .delimited()
                .delimiter(",")
                .names("account", "amount", "status")
                .targetType(Payment.class)
                .build();
    }

    @Bean
    public Step paymentStep(JobRepository jobRepository,
                            PlatformTransactionManager transactionManager,
                            PaymentItemProcessor processor
            , JdbcBatchItemWriter<Payment> writer) {
        return new StepBuilder("paymentStep", jobRepository)
                .<Payment, Payment>chunk(100, transactionManager)
                .reader(multiResourceReaderThreadSafe())
                .processor(processor)
                .writer(writer)
                .taskExecutor(new VirtualThreadTaskExecutor("VirtualThread-"))
                .build();
    }
}