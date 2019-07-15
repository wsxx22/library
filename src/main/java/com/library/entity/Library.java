package com.library.entity;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class Library {

    private Set<BookStack> titlesInLibrary = new HashSet<>();
    private List<Borrowing> borrowing = new ArrayList<>();
    private static Library instance = new Library();

    private Library() {}

    public static Library getInstance() {
        return instance;
    }

    public void addBook (Book book) {
        BookStack bookStack = new BookStack(book.getTitle(), book.getAuthor());
        bookStack.addId(book.getId());
        instance.titlesInLibrary.add(bookStack);
    }

    public void borrowBook (Reader reader, String title, String author) {

        BookStack bookStack = containInLibrary(title, author);

        if (bookStack != null & isTitleBorrowedByReader(reader, title)){

            Book book = new Book(bookStack.getId().iterator().next(), bookStack.getTitle(), bookStack.getAuthor());
            bookStack.getId().remove(book.getId());
            if (bookStack.getId().isEmpty()) {
                instance.titlesInLibrary.remove(bookStack);
            }
            instance.borrowing.add(new Borrowing(reader, book, LocalDateTime.now(), LocalDateTime.now()));
        }
    }

    private BookStack containInLibrary(String title, String author) {
        return instance.titlesInLibrary.stream()
                .filter(bookStack -> bookStack.getTitle().equals(title) & bookStack.getAuthor().equals(author))
                .filter(bookStack -> bookStack.getId().size() > 0)
                .findAny().orElseThrow(() -> new RuntimeException("Nie ma takiej ksiazki"));
    }

    private boolean isTitleBorrowedByReader (Reader reader, String title) {
        return reader.getBorrowedBooks().stream().noneMatch(b -> b.getTitle().equals(title));
    }


}