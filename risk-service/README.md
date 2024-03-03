# Project title
Risk Service

## Motivation
Serve as Risk API to Money Transfer API

## Quality Gate
Ignored

## Features
- Evaluate the transaction risk

## Run locally
### Docker
````shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/risk-service
mvn package
docker build -t virtual-threads/risk-service .
docker run -p 8081:8081 virtual-threads/risk-service
````
### Command line
 ```shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/risk-service
````

#### Option 1

```shell
./mvnw spring-boot:run
```

#### Option 2
```shell
mvn package 
java -jar target/risk-service-0.0.1-SNAPSHOT.jar
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

The versions are available [here](../README.md).

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