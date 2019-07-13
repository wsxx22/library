package com.library.entity;

import lombok.Getter;
import java.util.UUID;

@Getter
public abstract class Book {

    private String id = UUID.randomUUID().toString().replaceAll("-", "").substring(0,7);
    private String title;

    public Book(String title) {
        this.title = title;
    }



}
