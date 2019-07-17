package com.library.entity;

import lombok.Getter;

@Getter
public class Reader {

    private int id;
    private String name;
    private int age;

    public Reader(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
