package com.library.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class BookStack {
    private String title;
    private String author;

    @Getter(AccessLevel.NONE)
    private final List<UUID> idList = new ArrayList<>();

    @Getter(AccessLevel.NONE)
    private final List<Borrowing> borrowings = new ArrayList<>();

    public void addId(UUID uuid) {
        idList.add(uuid);
    }
    public void addBorrowing(Borrowing borrowing) {
        this.borrowings.add(borrowing);
    }

    public boolean isEmptyListId() {
        return this.borrowings.isEmpty();
    }

    public void removeIdAfterBorrow() {
        this.borrowings.remove(0);
    }

    public boolean isReaderInBorrowingExists(Reader reader) {
        return this.borrowings.stream()
                .anyMatch(borrowing -> borrowing.getReader().getId().equals(reader.getId()));
    }

    public boolean isNullEndDateTimeBorrowing() {
        return this.borrowings.stream()
                .map(Borrowing::getDateTimeEndBorrowing)
                .anyMatch(Objects::isNull);
    }

    public boolean isTitleBorrowedByReader(Reader reader) {
        return this.borrowings.stream()
                .filter(borrowing -> borrowing.getReader().getName().equals(reader.getName()))
                .anyMatch(borrowing -> borrowing.getDateTimeEndBorrowing() == null);
    }

    public void updateEndDateTimeBorrowing(LocalDateTime localDateTime) {
        Borrowing borrowing = this.borrowings.get(0);
        borrowing.setDateTimeEndBorrowing(localDateTime);
    }
}