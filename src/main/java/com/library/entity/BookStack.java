package com.library.entity;

import java.util.List;
import java.util.stream.Collectors;


public class BookStack {

    private Library library = Library.getInstance();

    public List<Book> getBooksByTitle(String title){
        return library.getBooksInLibrary().stream().filter(b -> b.getTitle().equals(title)).collect(Collectors.toList());
    }
}
