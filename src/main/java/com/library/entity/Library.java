package com.library.entity;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class Library {

    private Set<BookStack> titlesInLibrary = new HashSet<>();
    private List<Borrowing> borrowing = new ArrayList<>();

    public void addBook (Book book) {
        BookStack bookStack = new BookStack(book.getTitle(), book.getAuthor());
        bookStack.addId(book.getId());
        titlesInLibrary.add(bookStack);
    }

    public void borrowBook (Reader reader, String title, String author) {

        Optional<BookStack> bookStackOptional = bookExists(title, author);

        bookStackOptional
                .filter(b -> isTitleBorrowedByReader(reader, b.getTitle()))
                .map(bookStack -> addBorrowToReaderList(reader,bookStack))
                .filter(b -> b.getId().size() == 0)
                .map(b -> titlesInLibrary.remove(b));
    }

    private BookStack addBorrowToReaderList(Reader reader, BookStack bookStack) {
        Book book = new Book(
                bookStack.getId().iterator().next(), bookStack.getTitle(), bookStack.getAuthor());

        bookStack.getId().remove(book.getId());

        borrowing.add(new Borrowing(reader, book, LocalDateTime.now()));

        return bookStack;
    }

    private Optional<BookStack> bookExists(String title, String author) {

        return titlesInLibrary.stream()
                .filter(bookStack -> bookStack.getTitle().equals(title))
                .filter(bookStack -> bookStack.getAuthor().equals(author))
                .filter(bookStack -> bookStack.getId().size() > 0)
                .findAny();
    }

    private boolean isTitleBorrowedByReader (Reader reader, String title) {
        return reader.getBorrowedBooks().stream().noneMatch(b -> b.getTitle().equals(title));
    }

}