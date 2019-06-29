package org.delin.action;

import org.delin.ejb.AddBookBatchBeanRemote;
import org.delin.ejb.impl.AddBookBatchBean;
import org.delin.util.ejb.EJBLookUpUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import static org.delin.util.faces.FacesContext.getSessionMap;

@ManagedBean(name = "ConfirmAction")
@SessionScoped
public class ConfirmAction {
    public String processAction() {
        AddBookBatchBeanRemote addBookBatchBeanRemote = (AddBookBatchBeanRemote) getSessionMap().get("addBookBatchBeanRemote");
        if (addBookBatchBeanRemote == null) {
            addBookBatchBeanRemote = (AddBookBatchBeanRemote) EJBLookUpUtil.lookup(AddBookBatchBeanRemote.class, AddBookBatchBean.class, "Loser", "", true);
            getSessionMap().put("addBookBatchBeanRemote", addBookBatchBeanRemote);
        }
        try {
            addBookBatchBeanRemote.confirm();
            return "success";
        } catch (Exception exp) {
            return "error";
        }
    }
}
