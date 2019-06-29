package org.delin.service.impl;

import org.delin.dao.AbstractDAO;
import org.delin.dbconverter.BigDecimalToFloatConverter;
import org.delin.dbconverter.BookAuthorsConverter;
import org.delin.entities.BookEntity;
import org.delin.service.BookService;
import org.delin.util.db.template.OrmTemplate;
import org.delin.util.db.template.jpa.JpaTemplateImpl;

import java.util.List;

public class BookServiceImpl extends AbstractDAO<BookEntity> implements BookService {
    public BookServiceImpl() {
        addConverter("author", new BookAuthorsConverter());
        addConverter("price", new BigDecimalToFloatConverter());
    }

    @Override
    public void addBook(BookEntity book) {
        add(book);
    }

    @Override
    protected String getTable() {
        return "book";
    }

    @Override
    protected Class<BookEntity> getClazz() {
        return BookEntity.class;
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
