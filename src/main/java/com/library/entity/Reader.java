package com.library.entity;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Reader {

    private int id;
    private String name;
    private int age;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Reader(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
