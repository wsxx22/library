package com.library.service;

import com.library.entity.Book;
import com.library.entity.Borrowing;
import com.library.entity.Reader;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class LibraryService {

    private Set<String> titlesInLibrary = new HashSet<>();
    private List<Book> booksInLibrary = new ArrayList<>();

    private List<Borrowing> borrowing = new ArrayList<>();

    public void addBook (Book book) {
        titlesInLibrary.add(book.getTitle());
        booksInLibrary.add(book);
    }

    public void borrowBook (Reader reader, String title) {

        if (titlesInLibrary.contains(title) & isTitleBorrowedByReader(reader, title)) {
            Book book = booksInLibrary.stream().filter(b -> b.getTitle().equals(title)).findAny().get();
            booksInLibrary.remove(book);
            reader.getBorrowedBooks().add(book);

            if (booksInLibrary.stream().noneMatch(b -> b.getTitle().equals(book.getTitle()))) {
                titlesInLibrary.remove(book.getTitle());
            }
            borrowing.add(new Borrowing(reader, LocalDateTime.now(), LocalDateTime.now()));
            System.out.println(book.getId() + ", ksiazka " + book.getTitle() + ", wypozyczona przez " + reader.getName());
        }
    }

    private boolean isTitleBorrowedByReader (Reader reader, String title) {
        return reader.getBorrowedBooks().stream().noneMatch(b -> b.getTitle().equals(title));
    }



}
