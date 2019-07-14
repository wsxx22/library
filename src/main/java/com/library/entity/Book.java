package com.library.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class Book {

    private final UUID id = UUID.randomUUID();
    private String author;
    private String title;

}
