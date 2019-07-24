package com.library;

import com.library.entity.Book;
import com.library.entity.BookStack;
import com.library.entity.Borrowing;
import com.library.entity.Reader;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class Library {

    private Set<BookStack> titlesInLibrary = new HashSet<>();

    public void addBook(String title, String author) {
        getBookStackIfExists(title, title)
                .ifPresent(bookStack -> addBookStack(title, author));
    }

    public void borrowBook(Reader reader, String title, String author) {
        getBookStackIfExists(title, author)
                .filter(bookStack -> isTitleBorrowedByReader(reader, bookStack))
                .map(bookStack -> addBorrow(reader, bookStack))
                .filter(bookStack -> bookStack.getId().size() == 0)
                .map(bookStack -> titlesInLibrary.remove(bookStack));
    }

    public void returnBook(Reader reader, Book book) {
        if (isBookInBookStackExists(book)) {
            titlesInLibrary.add(new BookStack(book.getTitle(), book.getAuthor()));
        }

        getBookStackIfExists(book.getTitle(), book.getAuthor())
                .filter(bookStack -> isBorrowReader(reader, bookStack))
                .filter(this::isNullDateTimeEndBorrowing)
                .ifPresent(bookStack -> {
                    bookStack.addId(book.getId());
                    bookStack.updateEndDateTimeBorrowing(LocalDateTime.now());
                });
    }

    private boolean isBookInBookStackExists(Book book) {
        return titlesInLibrary.stream()
                .filter(bookStack -> !bookStack.getTitle().equals(book.getTitle()))
                .noneMatch(bookStack -> bookStack.getAuthor().equals(book.getAuthor()));
    }

    private boolean isBorrowReader(Reader reader, BookStack bookStack) {
        return bookStack.getBorrowing().stream()
                .anyMatch(borrowing -> borrowing.getReader().getId().equals(reader.getId()));
    }

    private boolean isNullDateTimeEndBorrowing(BookStack bookStack) {
        return bookStack.getBorrowing().stream()
                .anyMatch(borrowing -> borrowing.getDateTimeEndBorrowing() == null);
    }

    private BookStack addBorrow(Reader reader, BookStack bookStack) {
        bookStack.getId().remove(bookStack.getId().get(0));
        bookStack.addBorrowing(new Borrowing(reader));
        return bookStack;
    }

    private Optional<BookStack> getBookStackIfExists(String title, String author) {
        return titlesInLibrary.stream()
                .filter(bookStack -> bookStack.getTitle().equals(title))
                .filter(bookStack -> bookStack.getAuthor().equals(author))
                .findAny();
    }

    private boolean isTitleBorrowedByReader(Reader reader, BookStack bookStack) {
        return bookStack.getBorrowing().stream()
                .filter(borrowing -> borrowing.getReader().getName().equals(reader.getName()))
                .anyMatch(borrowing -> borrowing.getDateTimeEndBorrowing() == null);
    }

    private boolean addBookStack(String title, String author) {
        BookStack bookStack = new BookStack(title, author);
        bookStack.addId(UUID.randomUUID());
        return titlesInLibrary.add(bookStack);
    }
}