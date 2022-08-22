package com.callor.book.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
도서 정보 관리를 하기 위하여 객체 추상화 하기
도서명, 출판사, 저자, 발행연도, 가격 등의 항목이 필요로 하다
이러한 항목을 기준으로 VO 클래스를 디자인하자

JPA 의 Entity 로 등록을 하고
물리적 table 로 구성하기

 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "tbl_books",schema = "bootdb")
public class BookVO {
    @Id // VO 클래스를 Entity 로 등록을 하면 반드시 PK 항목을 지정해 주어야 한다.
    /*
    isbn 처럼 규칙, 규격에 의해 자릿수가 정해진 문자열이 존재한다
    이때는 규격에 맞는 문자열을 최소한으로 지정하는 것이 좋다
     */
    @Column(length = 13)
    private String isbn;    // 도서코드(ISBN)

    @Column(length = 125,nullable = false)
    private String title;   // 도서명

    private String comp;    // 출판사
    private String author;  // 저자

    @Column(length = 10) // 2022-08-19
    private String pubdate; // 출판일

    /*
    변수형이 int 형일 경우
    기본값이 NOT NULL 로 설정되므로
    임의로 nullable 을 true 로 하여 NOT NULL 을 해제 한다
     */
    @Column(nullable = true)
    private int price;      // 정가
}
