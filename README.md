# Project title
Getting started using Virtual Threads

## Motivation
Understand the use of Java 21 and Virtual Threads on Rest, Batching and standalone apps

## Build status
[![Virtual Threads](https://github.com/luizgustavocosta/virtual-threads/actions/workflows/maven.yml/badge.svg?event=branch_protection_rule)](https://github.com/luizgustavocosta/virtual-threads/actions/workflows/maven.yml)

## Quality Gate
Ignored

## Features
- Transfer money
- Payments processor

## Run locally
- Download the project through the GitHub using
 ```shell
 git clone https://github.com/luizgustavocosta/virtual-threads.git
 cd virtual-threads 
 ```
- Navigating through the projects

| # | Name             | Type                           | Depends on items | How to run                         |
|---|------------------|--------------------------------|------------------|------------------------------------|
| 1 | bank-service     | Microservice                   | 5,7              | [Here](bank-service/README.md)     |
| 2 | batch-processing | Batch                          | -                | [Here](bank-service/README.md)     |
| 3 | k6               | Test                           | 1                | [Here](bank-service/README.md)     |
| 4 | resources        | Static files for documentation | -                | [Here](bank-service/README.md)     |
| 5 | risk-service     | Microservice                   | -                | [Here](bank-service/README.md)     |
| 6 | standalone       | Program                        | -                | [Here](standalone/README.md)       |
| 7 | transfer-service | Microservice                   | -                | [Here](transfer-service/README.md) |

## Languages and Tools:
<div>
  <img width=50px src="https://upload.wikimedia.org/wikipedia/commons/4/41/Duke_Wave.png">&nbsp;
  <img width=50px src="https://upload.wikimedia.org/wikipedia/commons/9/9c/IntelliJ_IDEA_Icon.svg">&nbsp;
  <img width=50px src="https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/javascript/javascript.png">&nbsp;
  <img width=50px src="https://upload.wikimedia.org/wikipedia/commons/e/ef/K6-logo.svg">&nbsp;
  <img width=150px src="https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg">&nbsp;
  <img width=150px src="https://upload.wikimedia.org/wikipedia/commons/5/52/Apache_Maven_logo.svg">&nbsp;
</div>
</br>

## Architecture:

### Transfer money
![img.png](resources/imgs/bank-transfer.png)

### Payments processor
![img.png](resources/imgs/payments-processor.png)

## References
* TBD


k6 run --out json=test.json hellok6.js
k6 run --out=cloud hellok6.js

