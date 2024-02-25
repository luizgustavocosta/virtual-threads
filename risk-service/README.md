# Project title
Risk Service

## Motivation
Serve as Risk API to Money Transfer API

## Quality Gate
Ignored

## Features
- Evaluate the transaction risk

## Run locally
 ```shell
 git clone https://github.com/luizgustavocosta/virtual-threads.git
 cd virtual-threads/risk-service
 mvn package 
 java -jar target/risk-service-0.0.1-SNAPSHOT.jar
 ```

```text
luizcosta@MacBook-Pro-de-Luiz risk-service % java -jar target/risk-service-0.0.1-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.2)

2024-02-25T18:43:20.754-03:00  INFO 5148 --- [risk-service] [           main] c.c.l.l.RiskServiceApplication           : Starting RiskServiceApplication v0.0.1-SNAPSHOT using Java 21.0.2 with PID 5148 (/Users/luizcosta/Downloads/temp/virtual-threads/risk-service/target/risk-service-0.0.1-SNAPSHOT.jar started by luizcosta in /Users/luizcosta/Downloads/temp/virtual-threads/risk-service)
2024-02-25T18:43:20.757-03:00  INFO 5148 --- [risk-service] [           main] c.c.l.l.RiskServiceApplication           : No active profile set, falling back to 1 default profile: "default"
2024-02-25T18:43:21.689-03:00  INFO 5148 --- [risk-service] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8081 (http)
2024-02-25T18:43:21.701-03:00  INFO 5148 --- [risk-service] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-02-25T18:43:21.701-03:00  INFO 5148 --- [risk-service] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.18]
2024-02-25T18:43:21.732-03:00  INFO 5148 --- [risk-service] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-02-25T18:43:21.733-03:00  INFO 5148 --- [risk-service] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 887 ms
2024-02-25T18:43:22.062-03:00  INFO 5148 --- [risk-service] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8081 (http) with context path ''
2024-02-25T18:43:22.079-03:00  INFO 5148 --- [risk-service] [           main] c.c.l.l.RiskServiceApplication           : Started RiskServiceApplication in 1.767 seconds (process running for 2.279)
```

## Languages and Tools:
<div>
  <img width=50px src="resources/imgs/java-vertical.svg">&nbsp;
  <img width=50px src="https://upload.wikimedia.org/wikipedia/commons/9/9c/IntelliJ_IDEA_Icon.svg">&nbsp;
  <img width=150px src="https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg">&nbsp;
  <img width=150px src="https://upload.wikimedia.org/wikipedia/commons/5/52/Apache_Maven_logo.svg">&nbsp;
</div>
</br>

## Architecture:

### Money transfer
![img.png](../resources/imgs/bank-transfer.png)

## API
This API has only endpoint that responds after 3 seconds, in order to simulate a I/O operation

### Examples of usage

#### Transfer
````shell
curl -X 'GET' \
  'http://localhost:8081/v1/risks/transaction/1234-5678/cellphone' \
  -H 'accept: */*'
````

### References
[Transaction Check](https://api-docs.fraud.net/docs/public-apis/395e4a8yfue3b-transaction-check)