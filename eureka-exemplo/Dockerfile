FROM java:8
EXPOSE 8761
ADD ./eureka-exemplo/target/eureka-exemplo-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]