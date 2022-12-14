# APIRestful-ecommerce ‍💻

## Aqui ficará parte da documentação do projeto, onde deixarei claro as tecnologias aplicadas, design, padrões e etc.

### Projeto inicializado usando o [Spring Initializr](https://start.spring.io/) 🍃

#### Neste projeto, será desenvolvido uma APIRestful, para gerenciamento das vendas de um Ecommerce, onde o diagrama conceitual e cardinalidades, foram elaborados como mostra a imagem abaixo.

![Diagrama](ecommerce/imagem/Diagrama.jpeg)

#

## Este projeto tem as seguintes dependências: 

 
DEPENDÊNCIAS | REFERÊNCIA
------------ | ---------------
SPRING WEB | https://mvnrepository.com/artifact/org.springframework/spring-web/5.3.22
LOMBOK | https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.24
JPA | https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/2.7.1
DEV TOOLS | https://spring.io/blog/2015/06/17/devtools-in-spring-boot-1-3
H2 DATABASE | http://www.h2database.com/html/build.html
BEAN VALIDATION | https://mvnrepository.com/artifact/javax.validation/validation-api


 

* Será usado JPA e o H2 Database, para persistir os dados do projeto.

* A camada ***domain***, é onde terá as entidades da API, onde acontece o  mapeamento e relacionamento delas, baseados no diagrama já elaborado acima.

* A camada de ***exception***, é onde está as exceções personalidas da nossa API.

* A camada de ***repositories***, segue o padrão Repository Pattern, reponsável por abstrair a persistência no banco de dados, com a responsabilidade desacoplada.

* A camada de ***controller***, é a intermediária entre outras camadas, faz o controle dos fluxos, e requisições.

* A camada de ***service***, é a responsável por toda a nossa lógica da regra de negócio.

* A camada de ***dto*** (*****DATA TRANSFER OBJECT*****) ele usa de um objeto mais simples, na aplicação usaremos o formato ***JSON***. **(terá uma imagem exemplo abaixo)** para transferir dados de um local a outro na aplicação, sem necessidade de regra de negócio em seus objetos, perceba que não precisamos explicitamente chamar todos os atributos dos objetos, e através do id, ele já abstrai a complexidade da transferência.

![JSON DTO](ecommerce/imagem/jsondto.jpeg)


## Foi usado inicialmente o [Postman](https://www.postman.com/) para validar o funcionamento correto dos end points desta API, conforme eu avançe no meu estudo, migrarei a documentação dos end points para o Swagger, segue abaixo o path deles.

### OBS: todo id entre {}, é onde recebe o id puro, ex: cliente/v1/api/1

ENTIDADE | END POINT                                 | MÉTODO HTTP | DETALHES
-------- |-------------------------------------------|------------| -------
CLIENTE  | http://localhost:8080/cliente/v1/api      | POST       | Salva um cliente no banco de dados, e gera 1 **id** auto incremento, os parametros recebidos no JSON sao, nome, cpf, e telefone.
CLIENTE  | http://localhost:8080/cliente/v1/api/{id} | GET        | Busca o cliente pelo **id**, tras informacoes como o id, nome, cpf, e telefone.
CLIENTE  | http://localhost:8080/cliente/v1/api/{id} | PUT        | Recebe o **id**  do cliente que quer atualizar, e os novos parametros desejados dele.
CLIENTE  | http://localhost:8080/cliente/v1/api/{id} | DELETE     | Recebe o ***id*** do cliente, e o deleta, acontece um hard delete, cliente some do banco de dados.
CLIENTE  | http://localhost:8080/cliente/v1/api      | GET        | Recebe um filtro de busca pelo cliente, através dos atributos, ***id***, ***nome***, ***cpf*** ***e etc***. e retorna uma lista com clientes, que possuam os dados filtrados.
 | |                                           | 
PRODUTO  | http://localhost:8080/produto/v1/api      | POST       | Salva um produto no banco de dados, e gera 1 **id** auto incremento, os parametros recebidos no JSON sao, descricao e valor unitario.
PRODUTO  | http://localhost:8080/produto/v1/api/{id} | GET        | Busca o produto pelo **id**, tras informacoes como o id, descricao e valor unitario.
PRODUTO  | http://localhost:8080/produto/v1/api/{id} | PUT        | Recebe o ***id***  do produto que quer atualizar, e os novos parametros desejados dele.
PRODUTO  | http://localhost:8080/produto/v1/api/{id} | DELETE     | Recebe o ***id*** do produto, e o deleta, acontece um hard delete, produto some do banco de dados.
PRODUTO  | http://localhost:8080/produto/v1/api      | GET        | Recebe um filtro de busca pelo produto, através dos atributos, ***id***, ***descricao***, ***e valor unitário***. e retorna uma lista com produtos, que possuam os dados filtrados.
 |  |                                           |
PEDIDO   | http://localhost:8080/pedido/v1/api       | POST       | Depois de já ter salvo as informações de cliente e produtos adiquiridos, esse método salva o pedido no banco de dados e gera 1 ***id*** que é autoincremento, ele recebe os seguintes parametros, id do cliente, o total, uma lista de items, onde dentro dessa lista tem o id do produto e quantidade.
PEDIDO   | http://localhost:8080/pedido/v1/api/{id}  | GET        | Recebe o ***id*** do pedido, e retorna todas as informações do mesmo, incluindo status, itens, quantidades, e valor total e etc.
PEDIDO   | http://localhost:8080/pedido/v1/api/{id}  | PATCH      | Recebe o ***id*** do pedido, e no corpo da reposta, recebe o novo status do pedido, exemplo: ***REALIZADO*** ou ***CANCELADO***, só recebe o id e o novos status de parametro.

