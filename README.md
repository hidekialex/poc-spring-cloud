## POC Spring Cloud

POC criado utilizando as tecnologias Spring Cloud, mais especificamente os módulos de API Gateway (Zuul) e Service Discovery (Eureka), Spring web pra implementação de serviços, MySQL para persistência e Docker para conteinerizar os módulos da solução, e Docker compose para orquestrar tudo isso. A poc consiste na criação de 2 microsserviços (um modulo de segurança e outro de um serviço qualquer). A intenção é estudar a separação de responsabilidades entre os microsserviços e o fluxo de segurança (gerar token, validação) nesse tipo de arquitetura.

Separei o Authorization server do restante dos serviço, esse microsserviço fica responsável unicamente para gerar os tokens de acesso. Para não onerar a rede e consequentemente o banco de dados coloquei suporte para token JWT (dessa forma não será necessário os outros serviços bater no Authorization server para validar o token, economizando rede e banco). E caso seja necessário escalar basta subir outro container do Authorization server que automaticamente ele se registrará no Eureka.

O restante dos serviçoes são independentes do Authorization server. Isso facilita no troubleshoot caso seja necessário encontrar gargalos no fluxo de autenticação. (Se o número de logins simultaneos apresentar "lerdeza" no sistema há uma grande chance do problema estar no modulo de autenticação)

Caso seja necessário escalar qualquer microsserviço basta subir um container do serviço desejado que ele irá se registrar no Eureka. O Api Gateway (Zuul) realiza o client side load balancer (ele usa o Ribbon por trás dos panos), e possui varias estratégias de LB (por default ele usa o round robin)

### Melhorias a fazer na arquitetura

* Caso os dados do usuário consultado não sejam mutáveis (e caso for, e a consistência não seja importante), pode ser necessário incluir um cache para não onerar o banco de dados.
* Ter um banco de dados separado somente para o modulo de segurança (não compartilha-lo com os outros serviços para não criar futuros gargalos)
* Kubernetes para escalar automaticamente os containers
* Ajustes gerais como externalizar configs, criar variaveis de ambiente, criar volume para o container de banco de dados.

### Instruções para rodar o projeto

Certifique-se que tenha instalado na maquina as seguintes ferramentas:

* Java (Runtime)
* Maven (e as devidas variáveis de ambiente configuradas para este e o item anterior)
* Docker
* Docker compose

Certifique-se que o seu usuario do SO tenha permissão para executar o script init.sh localizado na raiz do projeto, e que também possa executar comandos do docker e docker-compose (este último basta incluir o seu usuario no grupo docker)

Execute o seguinte comando na raiz do projeto:

    $ ./init.sh

As etapas executadas serão:

- 1 - build de cada projeto com o maven
- 2 - build das imagens Docker
- 3 - "subida" de todos os containers que compõem o sistema

## Abaixo segue a visualizaço macro da arquitetura adotada:

![alt text](https://raw.githubusercontent.com/hidekialex/poc-spring-cloud/master/Arquitetura.png)

