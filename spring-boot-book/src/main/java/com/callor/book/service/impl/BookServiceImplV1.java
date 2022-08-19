package com.callor.book.service.impl;

import com.callor.book.model.BookVO;
import com.callor.book.pesistence.BookDao;
import com.callor.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImplV1 implements BookService {

    private final BookDao bookDao;
    public BookServiceImplV1(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookVO> selectAll() {
        // 조건에 관계없이 데이터를 모두 SELECT 하기
        List<BookVO> bookList = bookDao.findAll();
        return bookList;
    }

    @Override
    public BookVO findById(String isbn) {
        /*
        일반적인 한개의 데이터를 Select 하는 코드
        BookVO bookVO = findById(isbn)
        if(bookVO != null || bookVO.isEmpty() {
            log.debug(bookVO.toString())
        }
        try {
            log.debug(bookVO.toString())
        } catch (Exeception e) {
            log.debug("NULL 값")
        }
        log.debug(" {} ",bookVO )

         */

        /*
        JPA 의 findById() 는 return type 이 Optional<T> 이다
        Optional type 은 데이터가 null 인 경우를 좀더 쉽게 처리할수 있도록
        도와주는 도구이다
         */
        Optional<BookVO> opBookVO
                = bookDao.findById(isbn);

        /*
        Optional 데이터에서 실제 필요한 데이터(bookVO)를 추출하기 위하여
        get() method 를 사용할수 있는데
        보통은 orElse() 와 같은 method 를 사용하여
        만약 포함된 데이터(bookVO)가 null 일 경우
        다른 값을 생성하여 bookVO 데이터가 절대 null 이 아니도록
        처리할수 있다
         */
        BookVO bookVO = opBookVO.orElse(new BookVO());
        return bookVO;
    }

    @Override
    public int insert(BookVO bookVO) {
        log.debug("서비스 Insert {}", bookVO);
        /*
        mybatis 에서는 insert 를 수행한 후 int 값을 return 한다
        이때 return 하는 값은 몇개의 데이터가 추가되었는지를 알려주는
        지표이다

        하지만 spring-data(JPA)에서는 Save 를 실행한후
        다시 자기 자신(save 를 실행한 데이터)를 return 한다

        spring-data 에서는 CRUD 에서 INSERT 와 UPDATE 를 별도로
        구분하지 않는다
        save() method 에 VO 를 전달하면
        1. 먼저 PK 를 기준으로 데이터를 SELECT 하여
            테이블에 데이터가 있는지 검사한다
        2. 만약 테이블에 PK 에 해당하는 데이터가 없으면
            INSERT 를 수행한다
        3. 또, 테이블에 PK 에 해당하는 데이터가 있으면
            UPDATE 를 수행한다
         */
        BookVO result = bookDao.save(bookVO);
        return 0;
    }

    @Override
    public int update(BookVO bookVO) {
        return 0;
    }

    @Override
    public int delete(BookVO bookVO) {
        return 0;
    }

    @Override
    public List<BookVO> findByTitle(String title) {
        return null;
    }

    @Override
    public List<BookVO> findByComp(String comp) {
        return null;
    }

    @Override
    public List<BookVO> findByAuthor(String author) {
        return null;
    }
}
