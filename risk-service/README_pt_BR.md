# Título do projeto
Risk Service

## Motivação
Servir a API de Transferência de dinheiro

## Quality Gate
Ignored

## Features
- Avaliar o risk da transação

## Executar localmente
### Docker
````shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/risk-service
mvn package
docker build -t virtual-threads/risk-service .
docker run -p 8081:8081 virtual-threads/risk-service
````
### Linha de comando
 ```shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/risk-service
````

#### Opção 1 (Recomendado)

```shell
./mvnw spring-boot:run
```

#### Opção 2
```shell
mvn package 
java -jar target/risk-service-0.0.1-SNAPSHOT.jar
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

As versões estão disponíveis aqui [here](../README.md).

## Arquitetura:

### Transferência de dinheiro
![img.png](../resources/imgs/bank-transfer.png)

## API
Esta API tem somente um endpoint que levará sempre no mínimo 3 segundos para responder, para simular uma operação de entrada/saída.

### Exemplos de uso

#### Transferência
````shell
curl -X 'GET' \
  'http://localhost:8081/v1/risks/transaction/1234-5678/cellphone' \
  -H 'accept: */*'
````

### Referências
[Transaction Check](https://api-docs.fraud.net/docs/public-apis/395e4a8yfue3b-transaction-check)