# poc-spring-batch

### Referências
    https://www.codeproject.com/Tips/5336563/Run-Database-and-GUI-Clients-in-Docker
    https://github.com/spring-projects/spring-batch/blob/main/spring-batch-core/src/main/resources/org/springframework/batch/core/schema-mysql.sql
### Metadados

    docker-compose up -d
    docker-compose stop


##### Acesso a inteface SGBD
Após subir o container podemos acessar o SGBD pelo link a seguir.

[Acessar cloudbeaver](http://localhost:5021/)

1. Criar um User/Password para cloudbeaver
2. Configurar acesso a Sql Server


        Host: SQLServer
        User: sa
    Password: MyPass@word