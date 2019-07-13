package com.library.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class Person {

    private int id;
    private String name;
    private int age;

}
