package org.delin.action;

import org.delin.ejb.AddBookBatchBeanRemote;
import org.delin.ejb.impl.AddBookBatchBean;
import org.delin.entities.BookEntity;
import org.delin.util.ejb.EJBLookUpUtil;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import java.util.Map;

import static org.delin.util.faces.FacesContext.getSessionMap;

public class AddBatchBookAction implements ActionListener {
    @Override
    public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
        AddBookBatchBeanRemote addBookBatchBeanRemote = (AddBookBatchBeanRemote) getSessionMap().get("addBookBatchBeanRemote");
        if (addBookBatchBeanRemote == null) {
            addBookBatchBeanRemote = (AddBookBatchBeanRemote) EJBLookUpUtil.lookup(AddBookBatchBeanRemote.class, AddBookBatchBean.class, "Loser", "", true);
            getSessionMap().put("addBookBatchBeanRemote", addBookBatchBeanRemote);
        }
        Map map = getSessionMap();
        addBookBatchBeanRemote.addBook((BookEntity) map.get("book"));
        map.remove("book");
        map.remove("selects");
    }
}
