package org.delin.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Arrays;
import java.util.List;

@FacesConverter("AuthorConverter")
public class AuthorConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        s = (String) new ChineseCodeConverter().getAsObject(facesContext, uiComponent, s);
        System.out.println("AuthorConverter" + s);
        return Arrays.asList(s.split("\\s*,\\s*"));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        List<String> list = (List<String>) o;
        return String.join(",", list);
    }
}
