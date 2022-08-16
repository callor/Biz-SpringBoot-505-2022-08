package com.callor.data.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

// VO 클래스를 JPA 의 Data Table 화 하기 위한 선언
@Entity // 지금부터 이 클래스는 Entity 설계를 위한 도구이다 선언

// bootDB 라는 Database 에 tbl_users 라는 이름으로
// UserVO 를 참조하여 table 이 있다고 가정 또는 강제 생성하라
@Table(name="tbl_users",schema = "bootDB")
public class UserVO {

    // seq BIGINT PRIMARY KEY AUTO_INCREMENT 설정 하기
    @Id // seq 칼럼이 PK 이다 라는 선언
    // MySQL Auto_increment 설정을 하여 Seq 값을 관리하라
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    // username VARCHAR(20) NOT NULL UNIQUE
    @Column(columnDefinition = "VARCHAR(20)",
            nullable = false,
            unique = true)
    private String username;

    // DB 종류에 관계없이 문자열 255자로 설정하라
    // NOT NULL 로 설정
    @Column(length = 255,nullable = false)
    private String password;

    @Column(length = 125)
    private String email;

    // @Column 을 지정하지 않으면
    // String 형의 경우 문자열(VARCHAR) 255 를 default 로 설정
    @Column(length = 20)
    private String phone;

    private String address;

    @Column(length = 20)
    private String realname;

    @Column(length = 20)
    private String nickname;

}
