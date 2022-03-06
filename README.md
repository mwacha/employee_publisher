# Publisher
* Projeto de testes utilizando as seguintes tecnologias:
* AWSp/ SNS e SQS
* Flyway p/ versionamento de banco de dados
* Docker com os containers: postgresql
* Jacoco
* Sonar
* Mokito

# Como executar
- Criar a imagem com o comando: `docker build -t employee_publisher .`
- Após, rodar o comando passando os parâmetros das credencias da aws: `AWS_KEY=${key} AWS_SECRET_KEY=${secret} docker-compose up` <br>
Substituir ${key} e ${secret} pelas suas credenciais.

# Problemas
- Como o JDK é mais restritivo desde a versão 16, você precisa passar alguns parâmetros para a JVM para executar o Google Java Formatter. Para isso, adicione o arquivo .mvn/jvm.config no diretório raiz do seu projeto com o seguinte conteúdo: <br>
`--add-exports jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED --add-exports jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED --add-exports jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED --add-exports jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED --add-exports jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED `
# Sonar
Este projeto utiliza o sonar, então precisamos ter o servidor criado  e o token gerado. <br>
Após ter o token, é necessário configurar o arquivo settings.xml para efetuarmos o login no sonar via token. <br>
Ex: <br>
```xml
<settings>
    <pluginGroups>
    <pluginGroup>org.sonarsource.scanner.maven</pluginGroup>
        </pluginGroups>
    <profiles>
        <profile>
            <id>sonar</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <sonar.host.url>
                    http://myserver:9000
                </sonar.host.url>
                <sonar.login>
                   seu_token
                </sonar.login>
            </properties>
        </profile>
    </profiles>
</settings>
```
- Executar o seguinte comando: `mvn clean compile verify sonar:sonar`