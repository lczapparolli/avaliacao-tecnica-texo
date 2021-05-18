# avaliacao-tecnica-texo
Projeto desenvolvido como avaliação técnica, sendo parte do processo de recrutamento da Texo IT

# Framework utilizado
Para desenvolver o projeto foram utilizados os frameworks e bibliotecas Spring Boot e Spring Framework, permitindo a execução do projeto com o servidor web embutido.

# Excecução
Para executar a aplicação é possível utilizar o servidor embutido, bastando executar o seguinte comando na raiz do projeto:

**Linux/MacOS:** ```./mvnw spring-boot:run```
**Windows:** ```mvnw spring-boot:run```

A aplicação será executada e um servidor estará ouvindo as requisições na porta 8080. Os seguintes endpoints estarão disponíveis:

`GET /`: A raiz da aplicação irá identificar se a aplicação está funcionando corretamente ou se não foi possível carregar o arquivo de dados.

`GET /reports/producers/byInterval`: O endpoint com a resposta para a avaliação, irá retornar os produtores com o maior e o menor intervalo entre as vitórias.

Para executar os testes, o comando é similar, bastando executar:

**Linux/MacOS:** ```./mvnw test```
**Windows:** ```mvnw test```

# Arquivo de dados 
O arquivo com os dados da aplicação está localizado em ```src/main/resources/static/movielist.csv```, bastando substituí-lo e reiniciar a aplicação para utilizar novos dados.
**Obs:** Os testes foram implementados considerando a base atual, portanto ao alterar o arquivo os testes irão falhar.
