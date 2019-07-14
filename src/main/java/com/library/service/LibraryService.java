package com.library.service;

import com.library.entity.Book;
import com.library.entity.Borrowing;
import com.library.entity.Reader;
import com.library.util.BookTitle;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class LibraryService {

    private Set<BookTitle> titlesInLibrary = new HashSet<>();
    private List<Book> booksInLibrary = new ArrayList<>();

    private List<Borrowing> borrowing = new ArrayList<>();

    public void addBook (Book book) {
        titlesInLibrary.add(book.getTitle());
        booksInLibrary.add(book);
    }

    public void borrowBook (Reader reader, Book book) {

        if (titlesInLibrary.contains(book.getTitle()) & isTitleBorrowedByReader(reader, book)) {
            booksInLibrary.remove(book);
            reader.getBorrowedBooks().add(book);

            if (booksInLibrary.stream().noneMatch(b -> b.getTitle().equals(book.getTitle()))) {
                titlesInLibrary.remove(book.getTitle());
            }
            borrowing.add(new Borrowing(reader, LocalDateTime.now(), LocalDateTime.now()));
            System.out.println(book.getId() + ", ksiazka " + book.getTitle().getTitle + ", wypozyczona przez " + reader.getName());
        }
    }

    private boolean isTitleBorrowedByReader (Reader reader, Book book) {
        return reader.getBorrowedBooks().stream().noneMatch(b -> b.getTitle().equals(book.getTitle()));
    }



}
