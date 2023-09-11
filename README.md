# votacao-desafio
Serviço de votação em pautas por associados.

#### Tecnologias utilizadas:
- Linguagem: _Java 17_
- Framework _SpringBoot_
- Gerenciador de dependências: _Maven_
- Utilização do _OpenFeing_ para a chamada externa de validação de cpf **(Tarefa Bônus 1)**.
- Utilização do _Kafka_ como fila de mensageria para envio de resultado da votação juntamente do do _Zookeeper_
que é a ferramenta que gerencia o estado dos cluster **(Tarefa Bônus 2)**.
- Para o versionamento da API foi utilizado o modelo de versionamento _"path/URI"_ **(Tarefa Bônus 4)**.
- Conteinerização utilizando o _Docker_ Kafka e Zookeeper_.
- Utilização do banco de dados NoSql MongoDB que é um banco de dados orientado a documentos muito utilizado,
este foi utilizado através do mongo atlas, que fornece uma instancia gratuita do banco de dados até 500mb.
- Utilização do _TimerTask e Timer_ para o fechamento da sessão de pauta na data e horário estipulado ou por
padrão em 1 minuto.
- Foram criados testes unitários com: _JUnit_ disponibilizando do framework _Mockito_.
- Utilização de _Swagger_ e _OpenApi_ na documentação do projeto.
- Utilização do _Lombok_ para a abstração de métodos assessores deixando o código mais limpo.

#### Observações:
- Utilizei o MongoDB pois é o banco NoSql mais utilizado.
- Utilizei ExceptionHandlers juntamente com exceptions personalizadas pois acho a maneira mais organizada de poder fazer validações,
além de ser completamente personalizável.

## Instruções para teste

###### Subir o container docker:
```
docker compose up -d
```
###### Rodar a classe main da aplicação:
```
VotacaoApplication.java
```
#### A aplicação rodará na porta localhost:8080

## Endpoints:

###### Criação de nova pauta:
```
POST - /v1/pauta?nome={nome}
```
###### Abertura de sessão de uma pauta:
```
PUT - /v1/pauta/abre-sessao?id={pauta_id}&data_expiracao={dd/MM/aaaa hh:MM:ss}
```
###### Busca resultado de uma pauta:
```
GET - /v1/pauta/resultado?id={pauta_id}
```
###### Criação de novo associado:
```
POST - /v1/associado?cpf={cpf}
```
###### Criação de novo voto:
```
POST - /v1/voto?associado_id={associado_id}&voto={SIM/NAO}&pauta_id={pauta_id}
```
##### Documentação

###### OpenAPI:
```
GET - http://localhost:8080/v1/api-docs
```
###### Swagger:
```
WEB - http://localhost:8080/v1/swagger-ui.html
```
