package org.delin.ejb.impl;

import org.delin.ejb.ListBooksBeanRemote;
import org.delin.entities.BookEntity;
import org.delin.service.BookService;
import org.delin.service.impl.BookServiceImpl;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ListBooksBean implements ListBooksBeanRemote {
    private BookService service = new BookServiceImpl();

    @Override
    public List<BookEntity> listAllBooks() {
        return service.listAllBooks();
    }
}
