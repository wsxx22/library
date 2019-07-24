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

    public boolean isEmptyListId() {
        return this.borrowing.isEmpty();
    }

    public void removeIdAfterBorrow() {
        this.borrowing.remove(this.borrowing.get(0));
    }

    public boolean isReaderInBorrowingExists(Reader reader) {
        return this.borrowing.stream()
                .anyMatch(borrowing -> borrowing.getReader().getId().equals(reader.getId()));
    }

    public boolean isNullEndDateTimeBorrowing() {
        return this.borrowing.stream()
                .anyMatch(borrowing -> borrowing.getDateTimeEndBorrowing() == null);
    }

    public boolean isTitleBorrowedByReader(Reader reader) {
        return this.borrowing.stream()
                .filter(borrowing -> borrowing.getReader().getName().equals(reader.getName()))
                .anyMatch(borrowing -> borrowing.getDateTimeEndBorrowing() == null);
    }

    public void updateEndDateTimeBorrowing(LocalDateTime localDateTime) {
        Borrowing borrowing = this.borrowing.get(0);
        borrowing.setDateTimeEndBorrowing(localDateTime);
    }
}