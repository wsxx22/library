package com.library.entity;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Reader extends Person {

    private List<Book> borrowedBooks = new ArrayList<>();

    public Reader(int id, String name, int age) {
        super(id, name, age);
    }

}
