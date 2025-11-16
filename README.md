# Desafio TJ JUD 📖

### 📋 Índice

* [Sobre](#-sobre)
* [Progresso](#-etapas)
* [Como Executar](#-como-executar)
* [Tecnologias](#-tecnologias)
* [Observações](#-observações)

---

### 📖 Sobre

Projeto para teste técnico. É um simples projeto para gerenciar livros, autores e assuntos.

---

### 🏗️ Progresso

- [x] Tela inicial
- [x] Cadastro de Livros
  - [x] Via API
  - [ ] Via Frontend
- [x] Cadastro de Assuntos
  - [x] Via API
  - [x] Via Frontend
- [x] Cadastro de Autores
  - [x] Via API
  - [ ] Via Frontend
- [x] Modelagem do banco de dados
- [x] Criação de View que agrupe os dados por autor
- [x] Geração de Relatório utilizando view
- [x] Containerizar a aplicação
- [ ] Utilizar Bootstrap

---

### 🚀 Como Executar

Clonar o repositório ou fazer o download do projeto. É necessário possuir o docker instalado em sua máquina.

Para rodar o projeto, basta rodar o seguinte comando:

```bash
docker compose up
```
ou caso queira usar docker compose no modo detached:
```bash
docker compose up -d
```

Esse comando irá gerar os pacotes do projeto frontend e backend, bem como também irá baixar a imagem do banco de dados utilizados. Tudo será configurado e rodará automaticamente.

Para testar a aplicação via web: [http://localhost:4200](http://localhost:4200)

Para testar apenas a API: [http://localhost:8080](http://localhost:8080)

---

### 🛠️ Tecnologias

As seguintes ferramentas e tecnologias foram utilizadas na construção do projeto:

* Java 17
* Spring Web 3
* Spring Boot 
* Spring Data
* Jasper Reports
* PostgreSQL 16
* Docker 3
* Maven 3
* Angular 20
* TypeScript
* HTML 5
* CSS 3
* Bootstrap 5
* Git e GitHub

---

### Observações

Apesar de não ter conseguido concluir todas as funcionalidades dentro do tempo proposto, continuarei trabalhando no projeto nos meus horários livres para finalizá-lo da melhor forma possível. Permanecerei atualizando o repositório conforme avançar