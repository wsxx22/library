package com.library.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class BookStack {
    private String title;
    private String author;
    private final List<UUID> id = new ArrayList<>();
    private final List<Borrowing> borrowing = new ArrayList<>();

    public void addId (UUID uuid) {
        id.add(uuid);
    }
    public void addBorrowing(Borrowing borrowing) {
        this.borrowing.add(borrowing);
    }

//    public Book getBook (BookStack bookStack) {
//        Book book = new Book(bookStack.getId().iterator().next(), bookStack.getTitle(), bookStack.getAuthor());
//        id.remove(book.getId());
//        return book;
//    }

    public BookStack updateEndDateTimeBorrowing (LocalDateTime localDateTime) {
        Borrowing borrowing = this.getBorrowing().get(0);
        borrowing.setDateTimeEndBorrowing(localDateTime);
        return this;
    }
}