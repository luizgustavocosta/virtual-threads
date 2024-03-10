# Título do projeto
Processador de pagamentos

## Motivação
Criar um exemplo do Spring Batch com Virtual Threads

## Features
- Processador de pagamentos

## Executar localmente
 ```shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/batch-processing
mvn package
java -jar target/batch-processing-0.0.1-SNAPSHOT.jar
 ```

```text
luizcosta@MacBook-Pro-de-Luiz batch-processing % java -jar target/batch-processing-0.0.1-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.2)

2024-02-25T18:59:49.092-03:00  INFO 5650 --- [           main] c.c.l.p.BatchProcessingApplication       : Starting BatchProcessingApplication v0.0.1-SNAPSHOT using Java 21.0.2 with PID 5650 (/Users/luizcosta/Downloads/temp/virtual-threads/batch-processing/target/batch-processing-0.0.1-SNAPSHOT.jar started by luizcosta in /Users/luizcosta/Downloads/temp/virtual-threads/batch-processing)
2024-02-25T18:59:49.096-03:00  INFO 5650 --- [           main] c.c.l.p.BatchProcessingApplication       : No active profile set, falling back to 1 default profile: "default"
2024-02-25T18:59:49.865-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration$Hikari' of type [org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration$Hikari] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:49.914-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'spring.datasource-org.springframework.boot.autoconfigure.jdbc.DataSourceProperties' of type [org.springframework.boot.autoconfigure.jdbc.DataSourceProperties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:49.916-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration$PooledDataSourceConfiguration' of type [org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration$PooledDataSourceConfiguration] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:49.917-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'jdbcConnectionDetails' of type [org.springframework.boot.autoconfigure.jdbc.PropertiesJdbcConnectionDetails] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:49.984-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'dataSource' of type [com.zaxxer.hikari.HikariDataSource] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:49.991-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration$JdbcTransactionManagerConfiguration' of type [org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration$JdbcTransactionManagerConfiguration] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:50.006-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizationAutoConfiguration' of type [org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizationAutoConfiguration] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:50.016-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'transactionExecutionListeners' of type [org.springframework.boot.autoconfigure.transaction.ExecutionListenersTransactionManagerCustomizer] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:50.023-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'spring.transaction-org.springframework.boot.autoconfigure.transaction.TransactionProperties' of type [org.springframework.boot.autoconfigure.transaction.TransactionProperties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:50.025-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'platformTransactionManagerCustomizers' of type [org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:50.033-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'transactionManager' of type [org.springframework.jdbc.support.JdbcTransactionManager] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:50.036-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'spring.batch-org.springframework.boot.autoconfigure.batch.BatchProperties' of type [org.springframework.boot.autoconfigure.batch.BatchProperties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor [jobRegistryBeanPostProcessor]? Check the corresponding BeanPostProcessor declaration and its dependencies.
2024-02-25T18:59:50.049-03:00  WARN 5650 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration$SpringBootBatchConfiguration' of type [org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration$SpringBootBatchConfiguration] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). The currently created BeanPostProcessor [jobRegistryBeanPostProcessor] is declared through a non-static factory method on that class; consider declaring it as static instead.
2024-02-25T18:59:50.083-03:00  INFO 5650 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-02-25T18:59:50.328-03:00  INFO 5650 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:a0bc82d1-2609-4bea-a25e-4e3c2c945580 user=SA
2024-02-25T18:59:50.330-03:00  INFO 5650 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2024-02-25T18:59:50.591-03:00  INFO 5650 --- [           main] o.s.b.c.step.builder.SimpleStepBuilder   : Setting commit interval to default value (1)
2024-02-25T18:59:50.798-03:00  INFO 5650 --- [           main] c.c.l.p.BatchProcessingApplication       : Started BatchProcessingApplication in 2.164 seconds (process running for 2.711)
2024-02-25T18:59:50.802-03:00  INFO 5650 --- [           main] o.s.b.a.b.JobLauncherApplicationRunner   : Running default command line with: []
2024-02-25T18:59:50.877-03:00  INFO 5650 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=transferJob]] launched with the following parameters: [{}]
2024-02-25T18:59:50.912-03:00  INFO 5650 --- [           main] .c.l.p.JobCompletionNotificationListener : Job 1 started at 2024-02-25T18:59:50.859124
2024-02-25T18:59:50.927-03:00  INFO 5650 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [paymentStep]
2024-02-25T18:59:56.498-03:00  INFO 5650 --- [           main] o.s.batch.core.step.AbstractStep         : Step: [paymentStep] executed in 5s570ms
2024-02-25T18:59:56.502-03:00  INFO 5650 --- [           main] .c.l.p.JobCompletionNotificationListener : Job 1 finished at 2024-02-25T18:59:56.502377 took PT5.643253S
2024-02-25T18:59:56.546-03:00  INFO 5650 --- [           main] .c.l.p.JobCompletionNotificationListener : Were processed 18286 payments. 13669 OK and 4617 SKIP
2024-02-25T18:59:56.689-03:00  INFO 5650 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=transferJob]] completed with the following parameters: [{}] and the following status: [COMPLETED] in 5s608ms
2024-02-25T18:59:56.698-03:00  INFO 5650 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2024-02-25T18:59:56.702-03:00  INFO 5650 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
```

## Linguagens e ferramentas:
<div>
  <img width=50px src="../resources/imgs/java-vertical.svg">&nbsp;
  <img width=50px src="https://upload.wikimedia.org/wikipedia/commons/9/9c/IntelliJ_IDEA_Icon.svg">&nbsp;
  <img width=150px src="https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg">&nbsp;
  <img width=150px src="https://upload.wikimedia.org/wikipedia/commons/5/52/Apache_Maven_logo.svg">&nbsp;
  <img width=100px src="https://upload.wikimedia.org/wikipedia/commons/a/a1/H2_logo.png">&nbsp;
</div>
</br>

## Arquitetura:

### Processador de pagamentos
![img.png](../resources/imgs/payments-processor.png)

### Referências
- [YouTube - Spring Batch](https://www.youtube.com/watch?v=vLw39E-pIiA)