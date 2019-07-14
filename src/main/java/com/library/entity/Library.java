package com.library.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Library {

    private List<Book> booksInLibrary = new ArrayList<>();
    private Set<BookStack> titlesInLibrary = new HashSet<>();
    private List<Borrowing> borrowing = new ArrayList<>();
    private static Library instance = new Library();

    private Library() {}

    public static Library getInstance() {
        return instance;
    }

}
