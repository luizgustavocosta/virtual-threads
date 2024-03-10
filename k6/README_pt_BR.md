# Título do projeto
Aplicação para teste de desempenho

## Motivação
Avaliar a desempenho da aplicação com uso de Virtual Threads

## Features
- N/A

## Executar
### k6
**Important** - k6 depende do serviço **bank** e das dependências funcionando corretamente.
 ```shell
git clone https://github.com/luizgustavocosta/virtual-threads.git
cd virtual-threads/k6
k6 run --out json=test.json load_test.js
 ```

## Linguagens e ferramentas:
<div>
  <img width=50px src="../resources/imgs/javascript.png">&nbsp;
  <img width=50px src="../resources/imgs/k6.png">&nbsp;
</div>
</br>

### Referências
- [K6](https://k6.io/docs/)