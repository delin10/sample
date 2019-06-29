package org.delin.action;

import org.delin.entities.BookEntity;
import org.delin.service.BookService;
import org.delin.service.impl.BookServiceImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean(name = "AddBookAction")
@SessionScoped
public class AddBookAction {
    private BookService service = new BookServiceImpl();

    public String processAction() {
        System.out.println("process Action----------");
        Map map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        BookEntity book = (BookEntity) map.get("book");
        map.remove("book");
        map.remove("selects");
        System.out.println(book);
        try {
            service.addBook(book);
            return "success";
        } catch (Exception exp) {
            exp.printStackTrace();
            return "error";
        }
    }
}
