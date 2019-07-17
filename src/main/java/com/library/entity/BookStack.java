package com.library.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class BookStack {

    private String title;
    private String author;

    private List<UUID> id = new ArrayList<>();
    private List<Borrowing> borrowing = new ArrayList<>();

    public BookStack(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public boolean addId (UUID uuid) {
        return id.add(uuid);
    }

    public void addBorrowing(Borrowing borrowing) {
        this.borrowing.add(borrowing);
    }

}
