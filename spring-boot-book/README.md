# Spring-boot Data Project
## 도서정보 관리 프로젝트

## Spring-Data Project
* spring-data project 는 DBMS 와 연동하여 CRUD 를 구현하는 Project
* spring-JPA(Java Persistence API, Architecture) :  
ORM(Object Relation Mapping)     
일반적으로 DBMS 와 연동하기 위해서는 SQL 문법, 명령구조 등을 필수로 익혀야 한다.  
Java 개발자들 중에 SQL 문법에 대해 어려움을 겪는 경우가 많다.  
그래서 SQL 을 사용하지 않고,  
Java 객체(클래스)만을 디자인하여 DBMS 와  
관련된 CRUD 를 구현하는 것을 목표로 탄생된 도구

## Spring-data-JPA 프로젝트 시작
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- MySQL 연동 JDBC 설정 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```
* Spring-data-jpa 프로젝트는 반드시 초기에 DB 설정이 있어야 한다
* DB 설정이 없을경우 프로젝트 시작 자체가 되지 않는다