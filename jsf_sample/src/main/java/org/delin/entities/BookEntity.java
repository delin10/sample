package org.delin.entities;

import org.delin.ejb.AddBookBatchBeanRemote;
import org.delin.ejb.ListBooksBeanRemote;
import org.delin.ejb.impl.AddBookBatchBean;
import org.delin.ejb.impl.ListBooksBean;
import org.delin.jpa.converter.BookAuthorsConverter;
import org.delin.util.EnumUtil;
import org.delin.util.ejb.EJBLookUpUtil;
import org.delin.view.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static org.delin.util.faces.FacesContext.getSessionMap;

@ManagedBean(name = "book")
@SessionScoped
@Entity
@Table(name = "book")
public class BookEntity implements Serializable {
    @Id
    private String isbn;
    private String name;
    @Convert(converter = BookAuthorsConverter.class)
    private List<String> author;//Jpa如何转换
    private Date publishTime;
    private float price;
    private String firstType;
    private String secondType;

    public String getFirstType() {
        return firstType;
    }

    //出现转换异常会导致无法触发事件
    public void setFirstType(String firstType) {
        System.out.println("setFirstType " + firstType);
        this.firstType = firstType;
    }

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        System.out.println("setSecondType " + secondType);
        this.secondType = secondType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void firstTypeChanged(ValueChangeEvent e) {
        System.out.println(e);
    }

    public String getFormatFirstType() {
        return OneClassType.valueOf(firstType).getLabel();
    }

    public String getFormatSecondType() {
        Enum res = EnumUtil.get(secondType, ComputerType.class, ManagementType.class, LiteratureType.class, OtherType.class);
        return res.toString();
    }

    public List<BookEntity> getBookList() {
        ListBooksBeanRemote listBooksBeanRemote = (ListBooksBeanRemote) EJBLookUpUtil.lookup(ListBooksBeanRemote.class, ListBooksBean.class, "Loser", "", false);
        return listBooksBeanRemote.listAllBooks();
    }

    public List<BookEntity> getUncommitedBookList() {
        AddBookBatchBeanRemote addBookBatchBeanRemote = (AddBookBatchBeanRemote) getSessionMap().get("addBookBatchBeanRemote");
        if (addBookBatchBeanRemote == null) {
            addBookBatchBeanRemote = (AddBookBatchBeanRemote) EJBLookUpUtil.lookup(AddBookBatchBeanRemote.class, AddBookBatchBean.class, "Loser", "", true);
            getSessionMap().put("addBookBatchBeanRemote", addBookBatchBeanRemote);
        }
        return addBookBatchBeanRemote.getBooks();
    }
}

