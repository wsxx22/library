package com.library;

import com.library.entity.Book;
import com.library.entity.Library;
import com.library.entity.Reader;
import com.library.service.LibraryService;

public class Main {

    public static void main(String[] args) {

        Library library = Library.getInstance();
        LibraryService libraryService = new LibraryService();
        Reader janek = new Reader(1,"Janek", 30);
        Reader marek = new Reader(2,"Marek", 40);

        libraryService.addBook(new Book("Robert C Martin", "Clean Code"));
        libraryService.addBook(new Book("Robert C Martin", "Clean Code"));
        libraryService.addBook(new Book("Joshua Bloch", "Effective Java"));


        System.out.println("=====before borrowed=====");
        for (String bt : library.getTitlesInLibrary()) {
            System.out.println(bt);
        }
        System.out.println("****************");
        for (Book b : library.getBooksInLibrary()) {
            System.out.println(b.getId() + ", " + b.getTitle());
        }
        System.out.println("=====before borrowed=====");
        System.out.println();


        libraryService.borrowBook(janek, "Clean Code");
        libraryService.borrowBook(janek, "Clean Code");
        libraryService.borrowBook(janek, "Effective Java");

//        libraryService.borrowBook(marek, "Clean Code");


//        System.out.println("=====after borrowed=====");
//        for (String bt : libraryService.getTitlesInLibrary()) {
//            System.out.println(bt);
//        }
//        System.out.println("****************");
//        for (Book b : libraryService.getBooksInLibrary()) {
//            System.out.println(b.getId() + ", " + b.getTitle());
//        }
//        System.out.println("=====after borrowed=====");

        System.out.println("---");
        library.getBooksByPredicate(b -> b.getTitle().equals("Clean Code"));

    }

}
