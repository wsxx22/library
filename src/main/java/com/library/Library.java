package com.library;

import com.library.entity.Book;
import com.library.entity.BookStack;
import com.library.entity.Borrowing;
import com.library.entity.Reader;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
public class Library {

    private Set<BookStack> titlesInLibrary = new HashSet<>();

    public void addBook (Book book) {

        titlesInLibrary.stream()
                .filter(b -> b.getTitle().equals(book.getTitle()))
                .filter(b -> b.getAuthor().equals(book.getAuthor()))
                .findAny()
                .map(bookStack -> bookStack.addId(book.getId()))
                .orElseGet(() -> addBookStack(book));
    }

    public void borrowBook (Reader reader, String title, String author) {

        Optional<BookStack> bookStackOptional = bookExists(title, author);

        bookStackOptional
                .filter(bookStack -> isTitleBorrowedByReader(reader, bookStack))
                .map(bookStack -> addBorrowToReaderList(reader, bookStack))
                .filter(b -> b.getId().size() == 0)
                .map(b -> titlesInLibrary.remove(b));
    }

    private BookStack addBorrowToReaderList(Reader reader, BookStack bookStack) {

        Book book = new Book(bookStack.getId().iterator().next(), bookStack.getTitle(), bookStack.getAuthor());

        bookStack.getId().remove(book.getId());

        bookStack.addBorrowing(new Borrowing(reader, book, LocalDateTime.now()));

        return bookStack;
    }

    private Optional<BookStack> bookExists(String title, String author) {

        return titlesInLibrary.stream()
                .filter(bookStack -> bookStack.getTitle().equals(title))
                .filter(bookStack -> bookStack.getAuthor().equals(author))
                .filter(bookStack -> bookStack.getId().size() > 0)
                .findAny();
    }

    private boolean isTitleBorrowedByReader (Reader reader, BookStack bookStack) {

        return bookStack.getBorrowing().stream()
                .filter(borrowing -> borrowing.getReader().getName().equals(reader.getName()))
                .filter(borrowing -> borrowing.getDateTimeEndBorrowing() == null)
                .filter(borrowing -> borrowing.getBook().getTitle().equals(bookStack.getTitle()))
                .anyMatch(borrowing -> borrowing.getBook().getAuthor().equals(bookStack.getAuthor()));
    }

    private boolean addBookStack(Book book) {
        BookStack bookStack = new BookStack(book.getTitle(), book.getAuthor());
        bookStack.addId(book.getId());
        return titlesInLibrary.add(bookStack);
    }

}