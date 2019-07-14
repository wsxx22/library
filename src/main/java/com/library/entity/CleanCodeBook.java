package com.library.entity;

import com.library.util.BookTitle;
import lombok.Getter;

@Getter
public class CleanCodeBook extends Book {

    public CleanCodeBook(String author, BookTitle title) {
        super(author, title);
    }
}
