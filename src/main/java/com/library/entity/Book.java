package com.library.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class Book {

    private UUID id = UUID.randomUUID();
    private String title;
    private String author;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }
}
