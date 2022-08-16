# Spring Data Project

* Spring Data Project Dependencies
* stater-data-jdbc 는 Spring Data(JPA) 를 사용할때는  
호환성 때문에 사용하며
현재 사용하는 Spring-boot 버전에서는 선택사항이다

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>

```