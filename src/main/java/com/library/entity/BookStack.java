package com.library.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class BookStack {

    private String title;
    private String author;

    private Library library = Library.getInstance();

    public BookStack(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public List<Book> containBooks (String title, String author) {
        BookStack bookStack = new BookStack(title, author);
        if (isContainInLibrary(bookStack)) {
            return library.getBooksInLibrary().stream().filter(b -> b.getTitle().equals(title)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private boolean isContainInLibrary(BookStack bookStack) {
        return library.getTitlesInLibrary().stream()
                .anyMatch(b -> b.getTitle().equals(bookStack.getTitle()) & b.getAuthor().equals(bookStack.getAuthor()));
    }


}
