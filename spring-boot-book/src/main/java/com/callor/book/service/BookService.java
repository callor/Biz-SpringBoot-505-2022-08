package com.callor.book.service;

import com.callor.book.model.BookVO;

import java.util.List;

public interface BookService {

    public List<BookVO> selectAll();
    public BookVO findById(String isbn);
    public int insert(BookVO bookVO);
    public int update(BookVO bookVO);
    public int delete(BookVO bookVO);

    public List<BookVO> findByTitle(String title);
    public List<BookVO> findByComp(String comp);
    public List<BookVO> findByAuthor(String author);

}
