package org.delin.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

@ManagedBean(name = "selects", eager = true)
@SessionScoped
public class BookSelectItems implements ValueChangeListener {
    private OneClassType type;

    public OneClassType[] getOneClassTypes() {
        return OneClassType.values();
    }

    public Object getTwoClassTypes() {
        if (type == null) {
            return OtherType.values();
        }
        switch (type) {
            case COMPUTER:
                return ComputerType.values();
            case LITERATUER:
                return LiteratureType.values();
            case MANAGEMENT:
                return ManagementType.values();
            case OTHER:
            default:
                return new OneClassType[]{OneClassType.OTHER};
        }
    }

    @Override
    public void processValueChange(ValueChangeEvent valueChangeEvent) throws AbortProcessingException {
        System.out.println("typechanged" + valueChangeEvent);
        type = OneClassType.valueOf(valueChangeEvent.getNewValue().toString());
    }

    public void typeChanged(ValueChangeEvent e) {
        System.out.println("typechanged:" + e);
        System.out.println("typechanged:" + e.getNewValue());
        //用了值未converter.getAsO的值bject
        type = OneClassType.valueOf(e.getNewValue().toString());
    }
}
