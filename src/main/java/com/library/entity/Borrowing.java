package com.library.entity;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Borrowing {
    private final UUID id = UUID.randomUUID();
    private Reader reader;
    private final LocalDateTime dateTimeStartBorrowing;
    private LocalDateTime dateTimeEndBorrowing;

    public Borrowing(Reader reader) {
        this.reader = reader;
        this.dateTimeStartBorrowing = LocalDateTime.now();
    }

    public final void setDateTimeEndBorrowing(LocalDateTime dateTimeEndBorrowing) {
        this.dateTimeEndBorrowing = dateTimeEndBorrowing;
    }
}