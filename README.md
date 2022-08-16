# APIRestful-ecommerce

## Aqui ficará parte da documentação do projeto, onde deixarei claro as tecnologias aplicadas, design, padrões e etc.

### Projeto inicializado usando o [Spring Initializr](https://start.spring.io/)

#### Neste projeto, será desenvolvido uma APIRestful, para gerenciamento das vendas de um Ecommerce, onde o diagrama conceitual e cardinalidades, foram elaborados como mostra a imagem abaixo.

![Diagrama](ecommerce/imagem/Diagrama.jpeg)

#

## Este projeto tem as dependências: 

 
DEPENDÊNCIAS | REFERÊNCIA
------------ | ---------------
LOMBOK | https://projectlombok.org/
JPA | https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
DEV TOOLS | https://spring.io/blog/2015/06/17/devtools-in-spring-boot-1-3
H2 DATABASE | http://www.h2database.com/html/build.html


 

* Será usado JPA e o H2 Database, para persistir os dados do projeto.

* A camada domain, é onde terá as entidades da Api, onde acontece o  mapeamento delas, baseados no diagrama já elaborado acima.

* A camada de repositories, segue o padrão Repository Pattern, reponsável por abstrair a persistência no banco de dados, com a responsabilidade desacoplada.

