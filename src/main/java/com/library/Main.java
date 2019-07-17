package com.library;

import com.library.entity.Book;
import com.library.entity.Reader;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        Reader janek = new Reader(1,"Janek", 30);
        Reader marek = new Reader(2,"Marek", 40);

        library.addBook(new Book("Robert C Martin", "Clean Code"));
        library.addBook(new Book("Robert C Martin", "Clean Code"));
        library.addBook(new Book("Joshua Bloch", "Effective Java"));


//        libraryService.borrowBook(janek, "Clean Code");
//        libraryService.borrowBook(janek, "Clean Code");
        library.borrowBook(janek, "Effective Java", "Joshua Bloch");

    }

}
