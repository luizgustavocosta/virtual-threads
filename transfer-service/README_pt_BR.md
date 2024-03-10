# Título do projeto
Serviço de transferência

## Motivation
Servir como API de transferência de dinheiro.

## Features
- Transferência de dinheiro

## Executar localmente
### Docker
````shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/transfer-service
mvn package
docker build -t virtual-threads/transfer-service .
docker run -p 8082:8082 virtual-threads/transfer-service
````
### Linha de comando
```shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/transfer-service
````

#### Opção 1 (Recomendado)

```shell
./mvnw spring-boot:run
```

#### Opção 2
```shell
mvn package
java -jar target/transfer-service-0.0.1-SNAPSHOT.jar
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

As versões estão disponíveis aqui  [here](../README_pt_BR.md).

## Arquitetura:

### Transferência de dinheiro
![img.png](../resources/imgs/bank-transfer.png)

## API
Esta API possui um endpoint POST que responde após 1 segundo, para simular uma operação de I/O.

### Exemplos

#### Transferência
````shell
curl -X 'POST' \
   'http://localhost:8082/v1/transfers/1234-5678/9012-3456/100.01'
````

### Referências
[Wise - API Transfer](https://docs.wise.com/api-docs/api-reference/transfer)
[Docker](https://spring.io/guides/topicals/spring-boot-docker)