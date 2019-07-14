package com.library;

import com.library.entity.Book;
import com.library.entity.BookStack;
import com.library.entity.Reader;
import com.library.service.LibraryService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        BookStack bookStack = new BookStack();
        LibraryService libraryService = new LibraryService();

        Reader janek = new Reader(1,"Janek", 30);
        Reader marek = new Reader(2,"Marek", 40);

        libraryService.addBook(new Book("Robert C Martin", "Clean Code"));
        libraryService.addBook(new Book("Robert C Martin", "Clean Code"));
        libraryService.addBook(new Book("Joshua Bloch", "Effective Java"));


//        libraryService.borrowBook(janek, "Clean Code");
//        libraryService.borrowBook(janek, "Clean Code");
        libraryService.borrowBook(janek, "Effective Java", "Joshua Bloch");

        System.out.println("==========");
        List<Book> books = bookStack.containBooks("Clean Code", "Robert C Martin");
        books.forEach(b -> System.out.println(b.getId() + ", " + b.getTitle()));

    }

}
