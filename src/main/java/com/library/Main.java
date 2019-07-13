package com.library;

import com.library.entity.Book;
import com.library.entity.CleanCodeBook;
import com.library.entity.Reader;
import com.library.service.ReaderService;

public class Main {

    public static void main(String[] args) {

        ReaderService readerService = new ReaderService();
        Book cleanCode = new CleanCodeBook("CleanCode");
        Book effectiveJava = new CleanCodeBook("EffectiveJava");


        Reader janek = new Reader(1,"Janek", 30);

        readerService.borrowBook(janek, cleanCode);
        readerService.borrowBook(janek, effectiveJava);

        for (Book b : janek.getBorrowedBooks()) {
            System.out.println(b.getId());
            System.out.println(b.getTitle());
        }

    }


}
