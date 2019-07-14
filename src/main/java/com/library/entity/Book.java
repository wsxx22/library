package com.library.entity;

import com.library.util.BookTitle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@AllArgsConstructor
@Getter
public abstract class Book {

    private UUID id = UUID.randomUUID();
    private String author;
    private BookTitle title;

    public Book(String author, BookTitle title) {
        this.author = author;
        this.title = title;
    }





}
