package org.delin.ejb.impl;

import org.delin.ejb.AddBookBatchBeanRemote;
import org.delin.entities.BookEntity;
import org.delin.service.BookService;
import org.delin.service.impl.BookServiceImpl;

import javax.ejb.Stateful;
import java.util.LinkedList;
import java.util.List;

@Stateful
public class AddBookBatchBean implements AddBookBatchBeanRemote {
    private List<BookEntity> books = new LinkedList<>();
    private BookService service = new BookServiceImpl();

    @Override
    public void addBook(BookEntity book) {
        System.out.println("addBook "+book);
        System.out.println(this);
        books.add(book);
    }

    @Override
    public List<BookEntity> getBooks() {
        System.out.println(this);
        System.out.println("getBooks ============" + books);
        return books;
    }

    @Override
    public void confirm() {
        service.addBooks(books);
        books.clear();
    }
}
