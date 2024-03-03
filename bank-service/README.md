# Project title
Getting started using Virtual Threads

## Motivation
Understand the use of Java 21 and Virtual Threads on Rest, Batching and standalone apps.

## Quality Gate
Ignored

## Features
- Money transfer

## Run locally
### Docker
- This service depends on risk-service and transfer-service to run, not to be built as Docker image.
````shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/bank-service
mvn package
docker build -t virtual-threads/bank-service .
docker run -p 8080:8080 virtual-threads/bank-service
````
### Command line
- This service depends on risk-service and transfer-service.
 ```shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/bank-service
````

#### Option 1

```shell
./mvnw spring-boot:run
```

#### Option 2
```shell
mvn package 
java -jar target/bank-service-0.0.1-SNAPSHOT.jar
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
