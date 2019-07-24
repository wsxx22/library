package com.library;

import com.library.entity.Reader;
import org.openjdk.jol.vm.VM;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        Reader janek = new Reader(1L,"Janek", 30);
        Reader marek = new Reader(2L,"Marek", 40);

        library.addBook("Robert C Martin", "Clean Code");
        library.addBook("Robert C Martin", "Clean Code");
        library.addBook("Joshua Bloch", "Effective Java");

        System.out.println(VM.current().details());
        library.borrowBook(janek, "Effective Java", "Joshua Bloch");
    }
}