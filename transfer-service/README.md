# Project title
Transfer Service

## Motivation
Serve as Transfer API to Money Transfer API

## Features
- Transfer the money

## Run locally
### Docker
````shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/transfer-service
mvn package
docker build -t virtual-threads/transfer-service .
docker run -p 8082:8082 virtual-threads/transfer-service
````
### Command line
```shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/transfer-service
mvn package
java -jar target/transfer-service-0.0.1-SNAPSHOT.jar
 ```

```text
luizcosta@MacBook-Pro-de-Luiz transfer-service % java -jar target/transfer-service-0.0.1-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.2)

2024-02-25T16:22:32.865-03:00  INFO 2812 --- [transfer-service] [           main] c.c.l.t.TransferServiceApplication       : Starting TransferServiceApplication v0.0.1-SNAPSHOT using Java 21.0.2 with PID 2812 (/Users/luizcosta/Downloads/temp/virtual-threads/transfer-service/target/transfer-service-0.0.1-SNAPSHOT.jar started by luizcosta in /Users/luizcosta/Downloads/temp/virtual-threads/transfer-service)
2024-02-25T16:22:32.870-03:00  INFO 2812 --- [transfer-service] [           main] c.c.l.t.TransferServiceApplication       : No active profile set, falling back to 1 default profile: "default"
2024-02-25T16:22:34.120-03:00  INFO 2812 --- [transfer-service] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8082 (http)
2024-02-25T16:22:34.135-03:00  INFO 2812 --- [transfer-service] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-02-25T16:22:34.135-03:00  INFO 2812 --- [transfer-service] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.18]
2024-02-25T16:22:34.172-03:00  INFO 2812 --- [transfer-service] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-02-25T16:22:34.173-03:00  INFO 2812 --- [transfer-service] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1178 ms
2024-02-25T16:22:34.662-03:00  INFO 2812 --- [transfer-service] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8082 (http) with context path ''
2024-02-25T16:22:34.689-03:00  INFO 2812 --- [transfer-service] [           main] c.c.l.t.TransferServiceApplication       : Started TransferServiceApplication in 2.578 seconds (process running for 3.128)
```

## Languages and Tools:
<div>
  <img width=50px src="../resources/imgs/java-vertical.svg">&nbsp;
  <img width=50px src="https://upload.wikimedia.org/wikipedia/commons/9/9c/IntelliJ_IDEA_Icon.svg">&nbsp;
  <img width=150px src="https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg">&nbsp;
  <img width=150px src="https://upload.wikimedia.org/wikipedia/commons/5/52/Apache_Maven_logo.svg">&nbsp;
  <img width=150px src="../resources/imgs/archimate.png">&nbsp;
  <img width=180px src="../resources/imgs/docker-logo-blue.svg">&nbsp;
</div>
</br>

## Architecture:

### Money transfer
![img.png](../resources/imgs/bank-transfer.png)

## API
This API has a POST endpoint that responds after 1 second, in order to simulate a I/O operation

### Examples of usage

#### Transfer
````shell
curl -X 'POST' \
   'http://localhost:8082/v1/transfers/1234-5678/9012-3456/100.01'
````

### References
[Wise - API Transfer](https://docs.wise.com/api-docs/api-reference/transfer)
[Docker](https://spring.io/guides/topicals/spring-boot-docker)