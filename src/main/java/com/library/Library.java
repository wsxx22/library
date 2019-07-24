package com.library;

import com.library.entity.Book;
import com.library.entity.BookStack;
import com.library.entity.Borrowing;
import com.library.entity.Reader;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class Library {

    private Set<BookStack> titlesInLibrary = new HashSet<>();

    public void addBook(String title, String author) {
        getBookStackIfExists(title, title)
                .ifPresent(bookStack -> addBookStack(title, author));
    }

    public void borrowBook(Reader reader, String title, String author) {
        getBookStackIfExists(title, author)
                .filter(bookStack -> bookStack.isTitleBorrowedByReader(reader))
                .map(bookStack -> addBorrow(reader, bookStack))
                .filter(BookStack::isEmptyListId)
                .ifPresent(bookStack -> titlesInLibrary.remove(bookStack));
    }

    public void returnBook(Reader reader, Book book) {
        if (isBookInBookStackExists(book)) {
            titlesInLibrary.add(new BookStack(book.getTitle(), book.getAuthor()));
        }

        titlesInLibrary.stream()
                .filter(bookStack -> bookStack.isReaderInBorrowingExists(reader))
                .filter(BookStack::isNullEndDateTimeBorrowing)
                .findAny()
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

    private BookStack addBorrow(Reader reader, BookStack bookStack) {
        bookStack.removeIdAfterBorrow();
        bookStack.addBorrowing(new Borrowing(reader));
        return bookStack;
    }

    private Optional<BookStack> getBookStackIfExists(String title, String author) {
        return titlesInLibrary.stream()
                .filter(bookStack -> bookStack.getTitle().equals(title))
                .filter(bookStack -> bookStack.getAuthor().equals(author))
                .findAny();
    }

    private void addBookStack(String title, String author) {
        BookStack bookStack = new BookStack(title, author);
        bookStack.addId(UUID.randomUUID());
        titlesInLibrary.add(bookStack);
    }
}