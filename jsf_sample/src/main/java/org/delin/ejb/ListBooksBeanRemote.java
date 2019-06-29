package org.delin.ejb;

import org.delin.entities.BookEntity;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ListBooksBeanRemote {
    List<BookEntity> listAllBooks();
}
