package com.library.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
public class Library {

    private List<Book> booksInLibrary = new ArrayList<>();
    private Set<String> titlesInLibrary = new HashSet<>();
    private List<Borrowing> borrowing = new ArrayList<>();
    private static Library instance = new Library();

    private Library() {}

    public static Library getInstance() {
        return instance;
    }

    public void getBooksByPredicate(Predicate<Book> predicate) {
        List<Book> books = booksInLibrary.stream().filter(predicate).collect(Collectors.toList());

        for (Book b : books) {
            System.out.println("Tytul: " + b.getTitle() + ", Autor: " + b.getAuthor());
        }
    }

}
