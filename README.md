# 🏋️‍♂️ Fitstore

**Fitstore** é uma loja virtual de produtos e equipamentos fitness. Este projeto fullstack permite que usuários se cadastrem, façam login, explorem produtos, simulem pedidos e gerenciem suas compras em um ambiente funcional e moderno.

## 📦 Funcionalidades

- Cadastro e login de usuários
- Navegação por produtos e adição ao carrinho.
- Criação de pedidos e simulação de pagamento por vários meios (cartão, pix e boleto)
- Histórico de pedidos do cliente baseado por status (pago, pendente ou expirado)
- Edição de dados e foto de perfil do cliente

## 📽️ Demonstração do Projeto

Clique na imagem abaixo e veja a aplicação em funcionamento com todas as funcionalidades apresentadas:

[![Assista ao vídeo](https://img.youtube.com/vi/clA91S5pGUU/maxresdefault.jpg)](https://youtu.be/clA91S5pGUU)

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
- Git
- Node.js (18+)
- Docker e Docker Compose

### 2. Clone o repositório do backend
git clone https://github.com/chrismota/fitstore.git

cd fitstore

### 3. Renomeie o arquivo `.env.example` para `.env`

### 4. Suba os containers
docker-compose up --build -d

O backend estará disponível em: http://localhost:8080

### 5. Clone o repositório do frontend
git clone https://github.com/chrismota/fitstore-client.git

cd fitstore-client

### 6. Instale as dependências
npm install

### 7. Execute a aplicação
npm start

O frontend estará disponível em: http://localhost:4200

## 🗄 Banco de Dados e Imagens
O banco de dados é populado automaticamente via `fitstore_dump.sql`.
As imagens dos produtos estão em `s3mock-data`, simulando um ambiente AWS S3 local.


## 🛠️ Rotas Backend com Insomnia
O projeto inclui um arquivo de exportação do Insomnia (`fitstore.yaml`) com todas as rotas organizadas.

**Como usar:**
1. Abra o Insomnia.
2. Importe o arquivo `fitstore.yaml` (na raiz do projeto).
3. Todas as rotas estarão disponíveis, incluindo:

### 🧑‍💼 Rotas de Administrador:
- CRUD de produtos e imagens
- Edição e remoção de usuários
- Listagem de usuários, pedidos e pagamentos
- Gerenciamento de cupons

### 🙋‍♂️ Rotas de Cliente:
- Cadastro, login, pedidos, histórico e perfil

## 👤 Usuário Admin para Rotas Backend
Um usuário administrador está disponível para testes com as seguintes credenciais:

- Email: `admin@fitstore.com`
- Senha: `admin123`

## 👨‍💻 Autor
Christian Mota

[GitHub](https://github.com/chrismota)

