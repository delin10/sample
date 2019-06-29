package org.delin.service;

import org.delin.entities.BookEntity;

import java.util.List;

public interface BookService {
    void addBook(BookEntity book);

    List<BookEntity> listAllBooks();

    void addBooks(List<BookEntity> ls);
}
