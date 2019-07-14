package com.library;

import com.library.entity.Book;
import com.library.entity.CleanCodeBook;
import com.library.entity.Reader;
import com.library.service.LibraryService;
import com.library.util.BookTitle;

public class Main {

    public static void main(String[] args) {

        LibraryService libraryService = new LibraryService();

        Book cleanCode = new CleanCodeBook("Robert C Martin", BookTitle.CLEANCODE);
        Book cleanCode2 = new CleanCodeBook("Robert C Martin", BookTitle.CLEANCODE);
        Book effectiveJava = new CleanCodeBook("Joshua Bloch", BookTitle.EFFECTIVE_JAVA);

        libraryService.addBook(cleanCode);
        libraryService.addBook(cleanCode2);
        libraryService.addBook(effectiveJava);

        Reader janek = new Reader(1,"Janek", 30);

        System.out.println("=====before borrowed=====");
        for (BookTitle bt : libraryService.getTitlesInLibrary()) {
            System.out.println(bt.getTitle);
        }
        System.out.println("****************");
        for (Book b : libraryService.getBooksInLibrary()) {
            System.out.println(b.getId() + ", " + b.getTitle());
        }
        System.out.println("=====before borrowed=====");
        System.out.println();



        libraryService.borrowBook(janek, cleanCode);
        libraryService.borrowBook(janek, cleanCode2);
        libraryService.borrowBook(janek, effectiveJava);

        System.out.println("=====after borrowed=====");
        for (BookTitle bt : libraryService.getTitlesInLibrary()) {
            System.out.println(bt.getTitle);
        }
        System.out.println("****************");
        for (Book b : libraryService.getBooksInLibrary()) {
            System.out.println(b.getId() + ", " + b.getTitle());
        }
        System.out.println("=====after borrowed=====");

//        readerService.borrowBook(janek, cleanCode);
//        readerService.borrowBook(janek, effectiveJava);




    }


}
