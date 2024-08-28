# Teste Prático Pulse

Para Executar o projeto, siga os passos abaixo:

Usando o Maven:
1. Clone o repositório
2. Acesse a pasta do projeto
3. Execute o comando `mvn clean install` para instalar as dependências
4. Após instalar as dependências, execute o comando `mvn spring-boot:run`
5. Acesse o endereço `http://localhost:8443/swagger-ui/index.html` para acessar a documentação da API

Usando Docker:
1. Clone o repositório
2. Acesse a pasta do projeto
3. Execute o build da imagem `docker build -t estoque .`
4. Execute o container `docker run -it -p 8443:8443 estoque`
5. Acesse o endereço `http://localhost:8443/swagger-ui/index.html` para acessar a documentação da API


# Stacks utilizadas: 
- Spring boot versão 3x 
- Java 17 
- Docker 
- Banco de dados H2 
- Swagger com OpenAPI 
- Lombok 
- Mapstruct 
- Junit 
- Mockito
