# Project title
Getting started using Virtual Threads

## Motivation
Understand the use of Java 21 and Virtual Threads on Rest, Batching and standalone apps.

## Quality Gate
Ignored

## Features
- Money transfer

## Run locally
- This service depends on risk-service and transfer-service. 
- Please, start these 2 services before call the API's
 ```shell
 git clone https://github.com/luizgustavocosta/virtual-threads.git
 cd virtual-threads/bank-service
 mvn package 
 java -jar target/bank-service-0.0.1-SNAPSHOT.jar
 ```

```text
luizcosta@MacBook-Pro-de-Luiz bank-service % java -jar target/bank-service-0.0.1-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.2)

2024-02-25T18:20:15.315-03:00  INFO 4765 --- [virtual-threads] [           main] com.costa.luiz.bank.BankApp              : Starting BankApp v0.0.1-SNAPSHOT using Java 21.0.2 with PID 4765 (/Users/luizcosta/Downloads/temp/virtual-threads/bank-service/target/bank-service-0.0.1-SNAPSHOT.jar started by luizcosta in /Users/luizcosta/Downloads/temp/virtual-threads/bank-service)
2024-02-25T18:20:15.318-03:00  INFO 4765 --- [virtual-threads] [           main] com.costa.luiz.bank.BankApp              : No active profile set, falling back to 1 default profile: "default"
2024-02-25T18:20:18.273-03:00  INFO 4765 --- [virtual-threads] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-02-25T18:20:18.290-03:00  INFO 4765 --- [virtual-threads] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-02-25T18:20:18.291-03:00  INFO 4765 --- [virtual-threads] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.18]
2024-02-25T18:20:18.333-03:00  INFO 4765 --- [virtual-threads] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-02-25T18:20:18.334-03:00  INFO 4765 --- [virtual-threads] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2867 ms
2024-02-25T18:20:20.097-03:00  INFO 4765 --- [virtual-threads] [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 3 endpoint(s) beneath base path '/actuator'
2024-02-25T18:20:20.237-03:00  INFO 4765 --- [virtual-threads] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-02-25T18:20:20.262-03:00  INFO 4765 --- [virtual-threads] [           main] com.costa.luiz.bank.BankApp              : Started BankApp in 5.795 seconds (process running for 6.456)
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

## APIS:
[Swagger API](http://localhost:8080/swagger-ui/index.html)
[Actuator](http://localhost:8080/actuator)

### Examples

#### Transfer
````shell
curl -X 'POST' \
  'http://localhost:8080/v1/transfers' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "amount": "42.99",
  "from": "1234-5678",
  "to": "9012-3456",
  "device": "laptop"
}'
````

#### Parallel
````shell
curl -X 'GET' \
  'http://localhost:8080/v1/transfers/parallel' \
  -H 'accept: */*'
````

#### Concurrent
````shell
curl -X 'GET' \
  'http://localhost:8080/v1/transfers/concurrent' \
  -H 'accept: */*'
````

#### Old fashion
````shell
curl -X 'GET' \
  'http://localhost:8080/v1/transfers/old-fashion' \
  -H 'accept: */*'
````
