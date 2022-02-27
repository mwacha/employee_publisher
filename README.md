# Publisher
* Projeto de testes utilizando as seguintes tecnologias:
* AWSp/ SNS e SQS
* Flyway p/ versionamento de banco de dados
* Docker com os containers: postgresql

# Como executar
- Criar a imagem com o comando: `docker build -t employee_publisher .`
- Após, rodar o comando passando os parâmetros das credencias da aws: `AWS_KEY=${key} AWS_SECRET_KEY=${secret} docker-compose up` <br>
Substituir ${key} e ${secret} pelas suas credenciais.

