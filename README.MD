
<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/ivanqueiroz/horus?color=%2304D361">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/ivanqueiroz/horus">

  <a href="https://www.twitter.com/ivanqueiroz/">
    <img alt="Siga no Twitter" src="https://img.shields.io/twitter/url?url=https%3A%2F%2Fgithub.com%2Fivanqueiroz%2Fhorus">
  </a>

  <a href="https://github.com/ivanqueiroz/horus/commits/master">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/ivanqueiroz/horus">
  </a>

   <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen">
   <a href="https://github.com/ivanqueiroz/horus/stargazers">
    <img alt="Stargazers" src="https://img.shields.io/github/stars/ivanqueiroz/horus?style=social">
  </a>

  <a href="https://curriculo-ivanqueiroz.herokuapp.com/">
    <img alt="Feito por Ivan Queiroz" src="https://img.shields.io/badge/feito%20por-Ivan Queiroz-%237519C1">
  </a>

  <a href="https://ivanqueiroz.dev/">
    <img alt="Stargazers" src="https://img.shields.io/badge/Blog-Ivan Queiroz-%237159c1?style=flat&logo=ghost">
    </a>


</p>
<h1 align="center">
    <img alt="Horus - API de Conversão de Moedas" title="#Horus" src="./src/main/resources/static/images/horus.png" />
</h1>

<h4 align="center"> 
	🚧  Horus API :moneybag:️ Concluído 🚀 🚧
</h4>

<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#-funcionalidades">Funcionalidades</a> •
 <a href="#-layout">Layout</a> • 
 <a href="#-como-executar-o-projeto">Como executar</a> • 
 <a href="#-tecnologias">Tecnologias</a> • 
 <a href="#-contribuidores">Contribuidores</a> • 
 <a href="#-autor">Autor</a> • 
 <a href="#user-content--licença">Licença</a>
</p>


## 💻 Sobre o projeto

:moneybag: Horus - é uma API em REST que realiza a conversão entre moedas com cotações atualizadas constantemente.


Projeto desenvolvido durante desafio proposto pela Jaya Tech. O Horus pode ser consumido por sistemas financeiros que trabalham com cálculos entre moedas diferentes.

---

## ⚙️ Funcionalidades

- [x] Calculo de conversão entre as moedas:
    - [x] Real - Código de conversão **BRL**
    - [x] Iene - Código de conversão **JPY**
    - [x] Dólar - Código de conversão **USD**
    - [x] Euro - Código de conversão **EUR**

- [x] Regsitros de transações realizadas por um usuário.

---

## 🎨 Documentação da API

A documentação da aplicação está disponível em Swagger:

<a href="https://horus-api-converter.herokuapp.com/swagger-ui/index.html">
  <img alt="Made by ivanqueiroz" src="https://img.shields.io/badge/Acessar%20Documentação%20-Swagger-%2304D361">
</a>

### Web

<p align="center" style="display: flex; align-items: flex-start; justify-content: center;">
  <img alt="Horus" title="#Horus" src="./src/main/resources/static/images/horus-screen-shot.png" width="1027">
</p>

---

## 🚀 Como executar o projeto

Este projeto inicia um servidor na porta 8088, pronto para receber requisições REST.

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Java 11+](https://www.oracle.com/java/technologies/downloads/#java11) e o [Maven](https://maven.apache.org/).

Além disto é bom ter uma IDE para trabalhar com o código como [IDEA](https://www.jetbrains.com/pt-br/idea/download/) ou o [Spring Tools Suite](https://spring.io/tools).

Para testar a API é necessária uma ferramenta client REST como o [Postman](https://www.postman.com/downloads/).

#### 🎲 Rodando o Backend

```bash

# Clone este repositório
$ git clone git@github.com:ivanqueiroz/horus.git

# Acesse a pasta do projeto no terminal/cmd
$ cd horus

# Instale as dependências
$ ./mvnw clean install (Linux) ou ./mvnw.cmd clean install (Windows)

# Execute a aplicação em modo de desenvolvimento
$ ./mvnw spring-boot:run

# O servidor inciará na porta:8080 - acesse http://localhost:8080 

```

---

## 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

#### [](https://github.com/ivanqueiroz/horus#server-springboot)**Server**

- **[Spring Boot](https://spring.io/projects/spring-boot)** — Framework escolhido por além de ser _stand-alone_, requer menor esforço para iniciar o desenvolvimento das regras de negócio.
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)** — Escolhido para criar as operações com o banco de dados. Também reduziu o esforço para iniciar o desenvolvimento por criar as operações mais comuns (_CRUD_).
- **[Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)** — Permite a monitoração da aplicação remotamente. No Horus as permissões são: health,info,mappings,loggers,scheduledtasks e caches.
- **[Bean Validator](https://beanvalidation.org/2.0-jsr380/)** — Utilizado para validação dos valores passados para a API, permite a verificação através de anotações. Possui validações para os tipos mais conhecidos e permite a criação de validações próprias. No Horus há a validação dos valores permitidos para o tipo de moeda que utiliza a especificação.
- **[Lombok](https://projectlombok.org/)** — Diminui a quantidade de código escrito utilizando anotações para gerar trechos comuns durante a compilação. Utilizado no Horus para objetos do tipo _Data Transfer_ e para gerar a configuração de _log_ na classe.
- **[modelmapper](http://modelmapper.org/)** — Mapeador de valores de objetos. No projeto é utilizado na transferência de dados entre a camada mais externa para a interna da aplicação.
- **[MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)** — Um servidor web de teste programável. Utilizado nos testes de integração com a API externa do Exchange Rates.
- **[H2 Database](https://www.h2database.com/html/main.html)** — Banco de dados em memória (embedded). Para o propósito do projeto encaixou-se bem e é o banco principal que registra as transações de conversão. 
- **[SpringFox (Swagger)](https://springfox.github.io/springfox/)** — Plugin para o Spring Boot que gera a documentação da API do Horus.

> Veja o arquivo  [pom.xml](https://github.com/ivanqueiroz/horus/blob/master/pom.xml)

#### [](https://github.com/ivanqueiroz/horus#utilit%C3%A1rios)**Utilitários**

-   Projeto no Heroku:  **[Heroku](https://www.heroku.com/)**  →  **[Horus](https://horus-api-converter.herokuapp.com/)**

---

## 👨‍💻 Contribuidores

💜 Um super thanks 👏 para essa galera que fez esse produto sair do campo da ideia e entrar nas lojas de aplicativos :)

<table>
  <tr>
    <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars2.githubusercontent.com/u/2254731?s=400&u=0ba16a79456c2f250e7579cb388fa18c5c2d7d65&v=4" width="100px;" alt=""/><br /><sub><b>Diego Fernandes</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">👨‍🚀</a></td>
    <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars1.githubusercontent.com/u/4669899?s=460&u=806503605676192b5d0c363e4490e13d8127ed64&v=4" width="100px;" alt=""/><br /><sub><b>Cleiton Souza</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">👨‍🚀</a></td>
    <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars2.githubusercontent.com/u/861751?s=460&v=4" width="100px;" alt=""/><br /><sub><b>Robson Marques</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">👨‍🚀</a></td>
    <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars3.githubusercontent.com/u/16831337?s=460&v=4" width="100px;" alt=""/><br /><sub><b>Claudio Orlandi</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">🚀</a></td>
    <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars2.githubusercontent.com/u/37725197?s=460&u=446439436524c37f66e41f35b607dbb70358d5e4&v=4" width="100px;" alt=""/><br /><sub><b>Vinícios Fraga</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">🚀</a></td>
    <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars3.githubusercontent.com/u/26551306?s=460&u=18446655ccae6c2a29eb177a104ecf32f029aa3a&v=4" width="100px;" alt=""/><br /><sub><b>Hugo Duarte</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">🚀</a>  <a href="https://blog.rocketseat.com.br/" title="Blog">🌐</a></td>

  </tr>
  <tr>
    <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars0.githubusercontent.com/u/39345247?s=460&u=cdff2624a327a43e2765112a54e966a06eac6d79&v=4" width="100px;" alt=""/><br /><sub><b>Joseph Oliveira</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">🚀</a></td>
    <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars0.githubusercontent.com/u/10366880?s=460&u=59e93e1752e9d2ece4b7d8e129d60caba9c94207&v=4" width="100px;" alt=""/><br /><sub><b>Guilherme Rodz</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">🚀</a></td>
    <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars2.githubusercontent.com/u/6643122?s=460&u=1e9e1f04b76fb5374e6a041f5e41dce83f3b5d92&v=4" width="100px;" alt=""/><br /><sub><b>Mayk Brito</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">🚀</a></td>
    <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars2.githubusercontent.com/u/7268910?s=460&u=0b5d9df4232e70fa66ea9f130fad4260378323de&v=4" width="100px;" alt=""/><br /><sub><b>João Paulo</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">🚀</a></td>
    <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars2.githubusercontent.com/u/14251143?s=460&u=340ed1d854bbacc22b9a3210a18a1f589a28bc40&v=4" width="100px;" alt=""/><br /><sub><b>Luke Morales</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">🚀</a></td>
     <td align="center"><a href="https://rocketseat.com.br"><img style="border-radius: 50%;" src="https://avatars0.githubusercontent.com/u/5151405?s=460&u=1dbcf0e89087c2dc902d3331b90e532db1543d2b&v=4" width="100px;" alt=""/><br /><sub><b>Luiz Batanero</b></sub></a><br /><a href="https://rocketseat.com.br/" title="Rocketseat">🚀</a></td>

  </tr>
</table>

## 💪 Como contribuir para o projeto

1. Faça um **fork** do projeto.
2. Crie uma nova branch com as suas alterações: `git checkout -b my-feature`
3. Salve as alterações e crie uma mensagem de commit contando o que você fez: `git commit -m "feature: My new feature"`
4. Envie as suas alterações: `git push origin my-feature`
> Caso tenha alguma dúvida confira este [guia de como contribuir no GitHub](./CONTRIBUTING.md)

---

## 🦸 Autor

<a href="https://blog.rocketseat.com.br/author/thiago/">
 <img style="border-radius: 50%;" src="https://avatars3.githubusercontent.com/u/380327?s=460&u=61b426b901b8fe02e12019b1fdb67bf0072d4f00&v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Thiago Marinho</b></sub></a> <a href="https://blog.rocketseat.com.br/author/thiago/" title="Rocketseat">🚀</a>
 <br />

[![Twitter Badge](https://img.shields.io/badge/-@ivanqueiroz-1ca0f1?style=flat-square&labelColor=1ca0f1&logo=twitter&logoColor=white&link=https://twitter.com/ivanqueiroz)](https://twitter.com/ivanqueiroz) [![Linkedin Badge](https://img.shields.io/badge/-Thiago-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/ivanqueiroz/)](https://www.linkedin.com/in/ivanqueiroz/)
[![Gmail Badge](https://img.shields.io/badge/-ivanqueiroz@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:ivanqueiroz@gmail.com)](mailto:ivanqueiroz@gmail.com)

---

## 📝 Licença

Este projeto esta sobe a licença [MIT](./LICENSE).

Feito com ❤️ por Thiago Marinho 👋🏽 [Entre em contato!](https://www.linkedin.com/in/ivanqueiroz/)

---

##  Versões do README

[Português 🇧🇷](./README.md)  |  [Inglês sem emojis 🇺🇸](./README-en.md) | [Portugues sem logo  🇧🇷](./README-sem-logo.md) 
