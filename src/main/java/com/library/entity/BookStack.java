package com.library.entity;

import lombok.AccessLevel;
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

    @Getter(AccessLevel.NONE)
    private final List<UUID> idList = new ArrayList<>();

    @Getter(AccessLevel.NONE)
    private final List<Borrowing> borrowing = new ArrayList<>();

    public void addId(UUID uuid) {
        idList.add(uuid);
    }
    public void addBorrowing(Borrowing borrowing) {
        this.borrowing.add(borrowing);
    }

//    public Book getBook (BookStack bookStack) {
//        Book book = new Book(bookStack.getId().iterator().next(), bookStack.getTitle(), bookStack.getAuthor());
//        idList.remove(book.getId());
//        return book;
//    }

    public boolean isEmptyListId(BookStack bookStack) {
        return bookStack.borrowing.isEmpty();
    }

    public void removeIdAfterBorrow(BookStack bookStack) {
        bookStack.borrowing.remove(bookStack.borrowing.get(0));
    }

    public boolean isReaderInBorrowingExists(Reader reader, BookStack bookStack) {
        return bookStack.borrowing.stream()
                .anyMatch(borrowing -> borrowing.getReader().getId().equals(reader.getId()));
    }

    public boolean isNullEndDateTimeBorrowing(BookStack bookStack) {
        return bookStack.borrowing.stream()
                .anyMatch(borrowing -> borrowing.getDateTimeEndBorrowing() == null);
    }

    public boolean isTitleBorrowedByReader(Reader reader, BookStack bookStack) {
        return bookStack.borrowing.stream()
                .filter(borrowing -> borrowing.getReader().getName().equals(reader.getName()))
                .anyMatch(borrowing -> borrowing.getDateTimeEndBorrowing() == null);
    }

    public void updateEndDateTimeBorrowing(LocalDateTime localDateTime) {
        Borrowing borrowing = this.borrowing.get(0);
        borrowing.setDateTimeEndBorrowing(localDateTime);
    }
}