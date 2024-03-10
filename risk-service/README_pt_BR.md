# Título do projeto
Risk Service

## Motivação
Servir como API de risco para API de transferência de dinheiro

## Quality Gate
Ignorado

## Features
- Avalie o risco da transação

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
  <img width=50px src="../resources/imgs/Intellij.png">&nbsp;
  <img width=150px src="../resources/imgs/spring-framework.png">&nbsp;
  <img width=80px src="../resources/imgs/tomcat.png">
  <img width=150px src="../resources/imgs/maven.png">&nbsp;
  <img width=150px src="../resources/imgs/archimate.png">&nbsp;
  <img width=180px src="../resources/imgs/docker-logo-blue.svg">&nbsp;
</div>
</br>

As versões estão disponíveis [aqui](../README_pt_BR.md).

## Arquitetura:

### Transferência de dinheiro
![img.png](../resources/imgs/bank-transfer.png)

## API
Esta API possui apenas um endpoint que responde após 3 segundos, para simular uma operação de I/O.

### Exemplos

#### Avaliar o risco
````shell
curl -X 'GET' \
  'http://localhost:8081/v1/risks/transaction/1234-5678/cellphone' \
  -H 'accept: */*'
````

### Referências
[Transaction Check](https://api-docs.fraud.net/docs/public-apis/395e4a8yfue3b-transaction-check)