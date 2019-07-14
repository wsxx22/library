package com.library.service;

import com.library.entity.Book;
import com.library.entity.Borrowing;
import com.library.entity.Library;
import com.library.entity.Reader;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class LibraryService {

    Library library = Library.getInstance();

    public void addBook (Book book) {
        library.getTitlesInLibrary().add(book.getTitle());
        library.getBooksInLibrary().add(book);
    }

    public void borrowBook (Reader reader, String title) {

        if (library.getTitlesInLibrary().contains(title) & isTitleBorrowedByReader(reader, title)) {
            Book book = library.getBooksInLibrary().stream().filter(b -> b.getTitle().equals(title)).findAny().get();
            library.getBooksInLibrary().remove(book);
            reader.getBorrowedBooks().add(book);

            if (library.getBooksInLibrary().stream().noneMatch(b -> b.getTitle().equals(book.getTitle()))) {
                library.getTitlesInLibrary().remove(book.getTitle());
            }
            library.getBorrowing().add(new Borrowing(reader, LocalDateTime.now(), LocalDateTime.now()));
            System.out.println(book.getId() + ", ksiazka " + book.getTitle() + ", wypozyczona przez " + reader.getName());
        }
    }

    private boolean isTitleBorrowedByReader (Reader reader, String title) {
        return reader.getBorrowedBooks().stream().noneMatch(b -> b.getTitle().equals(title));
    }

}
