FROM maven:3.6-jdk-8-slim

EXPOSE 8080

RUN mkdir -p /opt/dev/teste-desenvolvedor-java/target
RUN chmod 777 -R /opt/dev/teste-desenvolvedor-java/target

#Arquivos necessários para aplicação
COPY ./pom.xml /opt/dev/teste-desenvolvedor-java/pom.xml
COPY ./src     /opt/dev/teste-desenvolvedor-java/src

COPY ./pom.xml /opt/dev/pom.xml

WORKDIR /opt/dev/teste-desenvolvedor-java/

#Criando o jar do projeto
RUN mvn package

WORKDIR /opt/dev/teste-desenvolvedor-java/target

#Iniciando o projeto
ENTRYPOINT ["java", "-jar", backend-0.0.1.jar"]