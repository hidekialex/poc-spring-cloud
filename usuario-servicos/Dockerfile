FROM java:8
EXPOSE 8080
ADD ./usuario-servicos/target/usuario-servicos-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]