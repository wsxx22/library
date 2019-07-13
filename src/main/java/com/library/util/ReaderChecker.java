package com.library.util;

import com.library.entity.Book;
import com.library.entity.Reader;

public interface ReaderChecker {

    void borrowBook(Reader reader, Book book);

}
