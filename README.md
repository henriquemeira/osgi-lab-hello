# Exemplo REST no Apache Karaf

## Resumo

Este exemplo demonstra a utilização do JAX-RS na implementação de serviço REST.

Este exemplo utiliza o blueprint para lidar com o jaxrs-server e Apache CXF como a implementação da especificação JAX-RS.

Implementa um serviço REST. 

## Artefatos

* **osgi-lab-hello-api** pacote (bundle) que contém o POJO `Person` e a interface `HelloPerson`.
* **osgi-lab-hello-service** pacote (bundle) que fornece a implementação da interface `HelloPerson` disponibilizando o serviço REST de acordo com o padrão whiteboard.
* **osgi-lab-hello-feature** fornece o repositório de recursos no padrão Karaf utilizado para a implantação.

## Construção (build)

A construção (build) utiliza o Apache Maven. No projeto raiz execute:

```
mvn clean install
```

## Recurso e Implantação

Na instância do Karaf em execução, registre o repositório de recursos utilizando:

```
karaf@root()> feature:repo-add mvn:co.h2a.sandbox.osgi/osgi-lab-hello-feature/LATEST/xml
```

Então, instale o recurso fornecido para acesso ao serviço:

```
karaf@root()> feature:install osgi-lab-hello-service
```

O recurso API será instalado automaticamente, pois é uma dependencia do serviço definido no repositório do recurso.


## Utilização

Uma vez instalado, você poderá acessar no browser o endereço: 

```
http://localhost:8181/helloperson
```

Ou via curl:

```
curl -v http://localhost:8181/helloperson
```

O retorno, no primeiro acesso, será um JSON sem informação.


Você pode adicionar novos registros conforme o exemplo abaixo:

```
curl -d '{"id":1,"name":"joao"}' -H 'Content-Type:application/json' -v -X POST http://localhost:8181/helloperson
```

Para chamar um método disponiblizado pelo servico `sayHello`, no browser acesse:

```
http://localhost:8181/helloperson/sayhello/1
```


## A fazer

* Incluir exemplos de chamada dos demais métodos para remoção e atualização de registros.
* Desenvolver projeto client com comandos de utilização dos serviços dentro do Karaf.

