package org.delin.service.impl;

import org.delin.dao.AbstractJpaDAO;
import org.delin.entities.BookEntity;
import org.delin.service.BookService;

import java.util.List;

public class BookServiceJpaImpl extends AbstractJpaDAO<BookEntity> implements BookService {
    @Override
    public void addBook(BookEntity book) {
        add(book);
    }

    @Override
    public List<BookEntity> listAllBooks() {
        return listAll();
    }

    @Override
    public void addBooks(List<BookEntity> ls) {
        addAll(ls);
    }
}
