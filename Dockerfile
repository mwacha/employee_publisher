
FROM maven:3.6.0-jdk-11-slim AS build

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline -B

COPY ./src ./src

RUN mvn -f ./pom.xml clean package

# Package stage
ARG AWS_KEY
ARG AWS_SECRET_KEY
FROM openjdk:11
COPY --from=build target/*.jar /usr/local/lib/employee_publisher.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/employee_publisher.jar"]