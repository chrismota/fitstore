# 🏋️‍♂️ Fitstore

**Fitstore** é uma loja virtual de produtos e equipamentos fitness. Este projeto fullstack permite que usuários se cadastrem, façam login, explorem produtos, simulem pedidos e gerenciem suas compras em um ambiente funcional e moderno.

## 📦 Funcionalidades

- Cadastro e login de usuários
- Navegação por produtos e adição ao carrinho.
- Criação de pedidos e simulação de pagamento por vários meios (cartão, pix e boleto)
- Histórico de pedidos do cliente baseado por status (pago, pendente ou expirado)
- Edição de dados e foto de perfil do cliente

## 🛠️ Tecnologias Utilizadas

### Backend
- Java 17
- Spring Boot
- PostgreSQL
- Flyway
- Amazon S3 (mock com S3Mock)
- Docker / Docker Compose


### Frontend 
- Angular 18 (Standalone Components)
- RxJS

## 🚀 Como Rodar o Projeto

### 1. Pré-requisitos

- [Git](https://git-scm.com/)
- [Node.js (18+)](https://nodejs.org/)
- [Angular CLI](https://angular.io/cli)
- [Docker e Docker Compose](https://www.docker.com/)

### 2. Clone o repositório do backend
git clone https://github.com/chrismota/fitstore.git

cd fitstore

### 3. Renomeie o arquivo .env.example apenas para .env
Isso carregará o arquivo contendo configurações do projeto.

### 4. Suba os containers
docker-compose up -d

### 5. Execute a aplicação
Backend disponível em: http://localhost:8080

### 6. Clone o repositório do frontend
git clone https://github.com/chrismota/fitstore-client

cd fitstore-client

### 7. Instale as dependências do projeto
npm install

### 8. Execute a aplicação
ng serve

O frontend estará disponível em: http://localhost:4200


## 🗄 Banco de Dados e Imagens
O banco de dados já vem populado automaticamente através de um arquivo fitstore_dump.sql.
As imagens dos produtos estão na pasta s3mock-data, simulando um ambiente AWS S3 local.


## 🛠️ Testes com Insomnia
O projeto inclui um arquivo de exportação do Insomnia com todas as rotas organizadas.

Como usar:
Abra o Insomnia.

Importe o arquivo fitstore.yaml presente na raiz do projeto.

As rotas estarão prontas para uso, incluindo rotas de administrador como:

- Adição, edição e remoção de produtos e de suas respectivas imagens.

- Edição de informações de um usuário.

- Remoção de usuário.

- Listagem de todos os usuários.

- Listagem de todos os pedidos dos usuários.

- Listagem de todos os pagamentos dos usuários.

- Adição e edição de cupons.

Além das rotas padrão de usuário cliente.

## 👤 Usuário Admin para Testes
Um usuário administrador está disponível para testes com as seguintes credenciais:

Email: admin@fitstore.com

Senha: admin123

## 👨‍💻 Autor
Christian Mota

[GitHub](https://github.com/chrismota)

