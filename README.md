# API Busca CEP

## Visão Geral
Esse projeto é uma API REST que contém um endpoint que trata requisições 
feitas com um CEP como parâmetro e retorna os seguintes dados:

- logradouro
- bairro
- cidade
- estado
- país
- código IBGE da cidade (se existir)
- código IBGE do estado (se existir)

O endpoint consome simultaneamente as três APIs a seguir:

https://viacep.com.br/

https://apicep.com/api-de-consulta/

https://brasilapi.com.br/docs#tag/CEP

Somente os dados da API que RESPONDE PRIMEIRO são considerados.


## Tecnologias e Padrões Utilizados

* Arquitetura Hexagonal
* Spring Boot
* Swagger UI
* CompletebleFuture
* Lombok
* MapStruct
* RestTemplate
* FeignClient
* Mockito Framework

## Executando o Projeto

### Instalar Docker
https://docs.docker.com/get-docker/

### Após instalação do Docker, seguir os passos:
1. Clonar este repositório
2. Vá para o diretório raiz do repositório: cd ./api-busca-cep
3. Rodar o comando: docker-compose up

### Verificando imagens docker rodando
```
docker ps
```
Para visualizar a documentação Swagger, acesse: http://localhost:8080/swagger-ui.html

