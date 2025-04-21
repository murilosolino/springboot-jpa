# springJpaHibernate

Projeto demo com finalidade de estudo em **Spring Boot** que demonstra operações **CRUD** usando **Spring Data JPA**, **Hibernate** e banco de dados **H2** em memória.

## Recursos

- Endpoints RESTful para as seguintes entidades:
    - **Usuário** (`User`)
    - **Pedido** (`Order`)
    - **Produto** (`Product`)
    - **Categoria** (`Category`)
    - **Item de Pedido** (`OrderItem`)
    - **Pagamento** (`Payment`)

- Carregamento automático de dados de exemplo ao executar com o perfil `test` (via `TestConfig.java`).
- Perfis Spring:
    - `default` – sem dados de exemplo (semelhante a produção)
    - `test` – banco H2 em memória pré-populado com dados de demonstração

## Tecnologias

- **Java 17**
- **Spring Boot 3.4.4**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database** (em memória)
- **Maven** (com wrapper `mvnw`)

## Pré-requisitos

- JDK **17** ou superior instalado e variável de ambiente `JAVA_HOME` configurada
- Git instalado

## Como executar

1. **Clone o repositório**
   ```bash
   git clone https://github.com/murilosolino/springboot-jpa.git
   cd springboot-jpa
   ```

2. **Compile e baixe as dependências**
   ```bash
   ./mvnw clean install
   ```

3. **Execute a aplicação**

    - Perfil padrão (sem dados de exemplo):
      ```bash
      ./mvnw spring-boot:run
      ```

    - Perfil de teste (com dados de exemplo):
      ```bash
      ./mvnw spring-boot:run -Dspring.profiles.active=test
      ```

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API

Todos os controllers estão no pacote `com.personalproject.springJpaHibernate.resources`.

| Método HTTP | URI                | Descrição                                |
|-------------|--------------------|------------------------------------------|
| GET         | `/users`           | Lista todos os usuários                  |
| GET         | `/users/{id}`      | Retorna um usuário pelo ID               |
| POST        | `/users`           | Cria um novo usuário                     |
| PUT         | `/users/{id}`      | Atualiza um usuário existente            |
| DELETE      | `/users/{id}`      | Remove um usuário                        |
| GET         | `/orders`          | Lista todos os pedidos                   |
| GET         | `/orders/{id}`     | Retorna um pedido pelo ID                |
| POST        | `/orders`          | Cria um novo pedido                      |
| PUT         | `/orders/{id}`     | Atualiza um pedido existente             |
| DELETE      | `/orders/{id}`     | Remove um pedido                         |
| ...         | ...                | Endpoints semelhantes para **products**, **categories**, **order items**, **payments** |

| Método HTTP | URI                | Descrição                                |
|-------------|--------------------|------------------------------------------|
| POST        | `/products/{id}/categories`| Adiciona uma categoria para o produto|
| DELETE      | `/products/{id}/categories`| Remove a categoria do produto    |
| POST        | `orders/{id}/items` | Adiciona um order item a uma order      |


## Dados de exemplo (perfil `test`)

Quando o perfil `test` está ativo, o `TestConfig` popula o H2 com:

- **3 categorias**: Eletrônicos, Livros, Computadores
- **5 produtos** de exemplo associados às categorias
- **2 usuários** de exemplo
- **3 pedidos** com diferentes status
- **Itens de pedido** ligados a pedidos e produtos
- **Pagamento** associado a um dos pedidos
