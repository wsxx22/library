package com.library;

import com.library.entity.Reader;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        Reader janek = new Reader(1L, "Janek", 30);
        Reader marek = new Reader(2L, "Marek", 40);

//        library.addBook("Robert C Martin", "Clean Code");
//        library.addBook("Robert C Martin", "Clean Code");
//        library.addBook("Joshua Bloch", "Effective Java");
//
//        library.borrowBook(janek, "Effective Java", "Joshua Bloch");

    }
}