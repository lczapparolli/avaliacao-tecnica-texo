# avaliacao-tecnica-texo
Projeto desenvolvido como avaliação técnica, sendo parte do processo de recrutamento da Texo IT

# Framework utilizado
Para desenvolver o projeto foram utilizados os frameworks e bibliotecas Spring Boot e Spring Framework, permitindo a execução do projeto com o servidor web embutido.

# Excecução
Para executar a aplicação é possível utilizar o servidor embutido, bastando executar o seguinte comando na raiz do projeto:

**Linux/MacOS:** ```./mvnw spring-boot:run```
**Windows:** ```mvnw spring-boot:run```

Para executar os testes, o comando é similar, bastando executar:

**Linux/MacOS:** ```./mvnw test```
**Windows:** ```mvnw test```

# Arquivo de dados 
O arquivo com os dados da aplicação está localizado em ```src/main/resources/static/movielist.csv```, bastando substituí-lo e reiniciar a aplicação para utilizar novos dados.
**Obs:** Os testes foram implementados considerando a base atual, portanto ao alterar o arquivo os testes irão falhar.