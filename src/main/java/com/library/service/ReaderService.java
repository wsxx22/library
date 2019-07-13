package com.library.service;

import com.library.util.ReaderChecker;
import com.library.entity.Book;
import com.library.entity.Reader;

public class ReaderService implements ReaderChecker {

    public void borrowBook(Reader reader, Book book) {
            if (reader.getBorrowedBooks().size() < 5) {
                reader.getBorrowedBooks().add(book);
            } else {
                System.out.println("Wiecej nie mozna wypozyczyc. Maksymalnie 5 sztuk.");
            }
    }

}
