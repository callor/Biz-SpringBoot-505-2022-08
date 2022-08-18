package com.callor.book.pesistence;

import com.callor.book.model.BookVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<BookVO, String> {




}
