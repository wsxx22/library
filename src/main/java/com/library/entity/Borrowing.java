package com.library.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Borrowing {

    private final UUID id = UUID.randomUUID();
    private Reader reader;
    private LocalDateTime dateTimeStartBorrowing;
    private LocalDateTime dateTimeEndBorrowing;

}
