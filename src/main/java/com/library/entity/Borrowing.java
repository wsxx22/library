package com.library.entity;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter

public class Borrowing {

    private final UUID id = UUID.randomUUID();
    private Reader reader;
    private Book book;
    private LocalDateTime dateTimeStartBorrowing;
    private LocalDateTime dateTimeEndBorrowing;


    public Borrowing(Reader reader, Book book, LocalDateTime dateTimeStartBorrowing) {
        this.reader = reader;
        this.book = book;
        this.dateTimeStartBorrowing = dateTimeStartBorrowing;
        this.dateTimeEndBorrowing = null;
    }
}
