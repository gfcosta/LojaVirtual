# LojaVirtual

Código fonte: [Loja Virtual - RESTful](https://wp.me/*********).

## Pré requisito

- Maven 3
- Java 8
- Jersey 1
- Hibernate 4

## Configurando Banco de Dados Oracle10g

- Script para criação das tabelas
```sh
$ cd LojaVirtual/src/main/resources
```
```imp ddl_loja_virtual.sql```

## Executando 

#### Servidor de aplicação Tomcat7

```sh
$ cd LojaVirtual
$ mvn tomcat7:run
```

## Cenários de teste

- Listar Cliente
```sh
$ curl -X GET -i http://localhost:8081/LojaVirtual/rest/cliente/listar
```

- Buscar Cliente
```sh
$ curl -X GET -i http://localhost:8081/LojaVirtual/rest/cliente/buscar/1
```

- Cadastrar Cliente
```sh
$ curl -X POST -i -H 'Content-type: application/json' http://localhost:8081/LojaVirtual/rest/cliente/cadastrar -d '{"nome": "teste teste teste","documento": "999.999.999-99","email": "teste@teste.com"}'
```

- Alterar Cliente
```sh
$ curl -X PUT -i -H 'Content-type: application/json' http://localhost:8081/LojaVirtual/rest/cliente/alterar -d '{"id": 1,"nome": "teste teste teste","documento": "999.999.999-99","email": "teste@teste.com"}'
```

- Excluir Cliente
```sh
$ curl -X DELETE -i http://localhost:8081/LojaVirtual/rest/cliente/excluir/1
```

- Listar Produto
```sh
$ curl -X GET -i http://localhost:8081/LojaVirtual/rest/produto/listar
```

- Buscar Produto
```sh
$ curl -X GET -i http://localhost:8081/LojaVirtual/rest/produto/buscar/1
```

- Cadastrar Produto
```sh
$ curl -X POST -i -H 'Content-type: application/json' http://localhost:8081/LojaVirtual/rest/produto/cadastrar -d '{"nome": "iPhone","preco": 1999.9,"quantidade": 10}'
```

- Alterar Produto
```sh
$ curl -X PUT -i -H 'Content-type: application/json' http://localhost:8081/LojaVirtual/rest/produto/alterar -d '{"id": 1,"nome": "iPhone","preco": 1999.9,"quantidade": 10}'
```

- Excluir Produto
```sh
$ curl -X DELETE -i http://localhost:8081/LojaVirtual/rest/produto/excluir/1
```

- Listar Carrinho
```sh
$ curl -X GET -i http://localhost:8081/LojaVirtual/rest/carrinho/listar
```

- Buscar Carrinho
```sh
$ curl -X GET -i http://localhost:8081/LojaVirtual/rest/carrinho/buscar/1
```

- Efetuar Compra
```sh
$ curl -X POST -i -H 'Content-type: application/json' http://localhost:8081/LojaVirtual/rest/compra/efetuar -d '{"cliente":{"id": 1,"nome": "teste teste teste","documento": "999.999.999-99","email": "teste@teste.com"},"produto":{"id": 1,"nome": "iPhone","preco": 1999.9,"quantidade": 10},"quantidade": 1}'
```

- Estornar Compra
```sh
$ curl -X DELETE -i http://localhost:8081/LojaVirtual/rest/compra/estornar/1
```
