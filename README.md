# Plataforma de ONG

## Descrição

A Plataforma de ONG é uma API que ajuda usuários a encontrarem ONGs e ajuda ONGs a serem encontradas. Usuários podem encontrar essas instituições, se voluntariar ou doar. Este projeto foi criado utilizando Maven e Spring Boot, e é documentado com Swagger. A plataforma também utiliza serviços da AWS, como S3 e EC2.

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **Maven**
- **Swagger** para documentação da API
- **AWS S3** para armazenamento de arquivos
- **AWS EC2** para hospedagem da aplicação

## Requisitos

- **Java 17+**
- **Maven 3.6.3+**
- **Docker** (opcional, para executar a aplicação em contêiner)

## Instalação

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/plataforma-ong.git
   cd plataforma-ong

2. Compile e construa o projeto::

   ```bash
   mvn clean install

3. Execute a aplicação:

   ```bash
   mvn spring-boot:run

## Documentação da API

A documentação da API está disponível via Swagger. Após iniciar a aplicação, acesse:

    ```bash
    http://localhost:8080/swagger-ui.html

## Executando com Docker

Você pode construir e executar a aplicação em um contêiner Docker.

Construa a imagem Docker:

    docker build -t plataforma-ong .

Execute o contêiner Docker:

    docker run -p 8080:8080 plataforma-ong

## Contribuindo

Se você deseja contribuir para este projeto, por favor, abra uma issue ou envie um pull request.

## Contato

Se você tiver alguma dúvida ou sugestão, sinta-se à vontade para entrar em contato.