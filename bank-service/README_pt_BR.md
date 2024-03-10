# Título do projeto
Bank Service

## Motivação
Criar um serviço que use Java 21 Virtual Threads e Spring 3.

## Quality Gate
Ignorado

## Features
- Transferência de dinheiro

## Executar localmente
### Docker
- Este serviço depende do risk-service e transfer-service para executar. Não é necessário na para construir a imagem Docker.
````shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/bank-service
mvn package
docker build -t virtual-threads/bank-service .
docker run -p 8080:8080 virtual-threads/bank-service
````
### Linha de comando
- Este serviço depende do risk-service e transfer-service.
 ```shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/bank-service
````

#### Opção 1 (Recomendado)

```shell
./mvnw spring-boot:run
```

#### Opção 2
```shell
mvn package 
java -jar target/bank-service-0.0.1-SNAPSHOT.jar
 ```

## Linguagens e ferramentas:
<div>
  <img width=50px src="../resources/imgs/java-vertical.svg">&nbsp;
  <img width=50px src="https://upload.wikimedia.org/wikipedia/commons/9/9c/IntelliJ_IDEA_Icon.svg">&nbsp;
  <img width=150px src="https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg">&nbsp;
  <img width=150px src="https://upload.wikimedia.org/wikipedia/commons/5/52/Apache_Maven_logo.svg">&nbsp;
  <img width=150px src="../resources/imgs/archimate.png">&nbsp;
  <img width=180px src="../resources/imgs/docker-logo-blue.svg">&nbsp;
</div>
</br>

As versões estão disponíveis aqui [here](../README_pt_BR.md).

## Arquitetura:

### Transferência de dinheiro
![img.png](../resources/imgs/bank-transfer.png)

## APIS:
[Swagger API](http://localhost:8080/swagger-ui/index.html)
[Actuator](http://localhost:8080/actuator)

### Exemplos

#### Transferência
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

#### Parallelo
````shell
curl -X 'GET' \
  'http://localhost:8080/v1/transfers/parallel' \
  -H 'accept: */*'
````

#### Concorrente
````shell
curl -X 'GET' \
  'http://localhost:8080/v1/transfers/concurrent' \
  -H 'accept: */*'
````

#### Modo antigo
````shell
curl -X 'GET' \
  'http://localhost:8080/v1/transfers/old-fashion' \
  -H 'accept: */*'
````
