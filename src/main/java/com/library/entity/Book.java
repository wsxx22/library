package com.library.entity;

import lombok.Value;
import java.util.UUID;

@Value
public class Book {
    private UUID id = UUID.randomUUID();
    private String title;
    private String author;
}