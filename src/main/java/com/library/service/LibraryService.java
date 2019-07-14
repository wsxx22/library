package com.library.service;

import com.library.entity.*;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class LibraryService {

    Library library = Library.getInstance();

    public void addBook (Book book) {
        library.getTitlesInLibrary().add(new BookStack(book.getTitle(), book.getAuthor()));
        library.getBooksInLibrary().add(book);
    }

    public void borrowBook (Reader reader, String title, String author) {

        if (containInLibrary(title, author) != null & isTitleBorrowedByReader(reader, title)) {
            Book book = library.getBooksInLibrary().stream().filter(b -> b.getTitle().equals(title)).findAny().get();
            library.getBooksInLibrary().remove(book);
            reader.getBorrowedBooks().add(book);

            if (library.getBooksInLibrary().stream().noneMatch(b -> b.getTitle().equals(book.getTitle()))) {
                library.getTitlesInLibrary().remove(containInLibrary(title, author));
            }
            library.getBorrowing().add(new Borrowing(reader, LocalDateTime.now(), LocalDateTime.now()));
            System.out.println(book.getId() + ", ksiazka " + book.getTitle() + ", wypozyczona przez " + reader.getName());
        }
    }

    private boolean isTitleBorrowedByReader (Reader reader, String title) {
        return reader.getBorrowedBooks().stream().noneMatch(b -> b.getTitle().equals(title));
    }

    private BookStack containInLibrary(String title, String author) {
        return library.getTitlesInLibrary().stream()
                .filter(bookStack -> bookStack.getTitle().equals(title) & bookStack.getAuthor().equals(author))
                .findAny().orElseThrow(() -> new RuntimeException("Nie ma takiej ksiazki"));
    }

}
