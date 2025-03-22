# Aula 02


## Arquivo de persistência

### Declaração padrão do arquivo de persistência:

<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
                                 http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd"
             version="2.2">


### Declarando qual implementação
<provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

### Configuração do banco de dados
<properties>
            <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/dra_aula01"/>
            <property name="javax.persistence.jdbc.user" value="root"/>
            <property name="javax.persistence.jdbc.password" value="root"/>

### Configuração da implementação

            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/> 
            <property name="hibernate.show_sql" value="true"/> 
            <property name="hibernate.format_sql" value="true"/> 
</properties>


## JPQL

Uma adaptação do JPA para comandos sql, JPQL é uma consulta sql orientada a objetos.

Exemplo: 
- SQL: SELECT * FROM pessoa
- JPQL: SELECT p FROM pessoa p

### Transactions

Toda ação que envolve alteração no banco de dados


### Static 

Algo que não muda independente do objeto instanciado, algo fixo da classe e que economiza memória. Além disso, não é necessário instanciar a classe para ter acesso a esse método. Pode gerar gargalo, pois fica estático e só pode ser gerado apenas um, ou seja, vários usuários terão acesso a mesma classe,objeto, método, etc.
