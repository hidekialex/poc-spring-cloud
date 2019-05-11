#!/bin/bash

echo '### Buildando os projetos envolvidos ###'
mvn -f ./eureka-exemplo/ clean package
mvn -f ./oauth2-exemplo/ clean package
mvn -f ./usuario-servicos/ clean package
mvn -f ./zuul-exemplo/ clean install

echo '### Criando as imagens docker dos projetos ###'
docker-compose build

echo '### Subindo containers ###'
docker-compose up
