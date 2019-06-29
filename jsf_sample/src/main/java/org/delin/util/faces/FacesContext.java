package org.delin.util.faces;

import java.util.Map;

public class FacesContext {
    public static Map<String,Object> getSessionMap(){
        return javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }
}
