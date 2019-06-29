package org.delin.converter;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

@FacesConverter("ChineseCodeConverter")
public class ChineseCodeConverter implements Converter {
    private static Set<String> uids = new HashSet<>();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String newValue) {
        String newstr = "";
        if (newValue == null) {
            newValue = "";
        }
        byte[] byte1 = null;
        try {
            //每次使用的不是同一个converter
            //新的实例
            System.out.println(this);
            System.out.println("uid:" + uids + ":" + newValue);
            System.out.println(uiComponent.getId());
            System.out.println(uiComponent.getClientId());
            //第一次进行编码转换即可
            //有时候第一次编码是正确的
            //启动服务器第一次访问得到错误值
            // 重新部署得到正确值
            if (false && uids.add(uiComponent.getClientId())) {
                byte1 = newValue.getBytes("ISO-8859-1");
                newstr = new String(byte1, "UTF-8");
                System.out.println(newstr);
            } else {
                newstr = newValue;
            }
            UIInput input = (UIInput) uiComponent;//
            input.setSubmittedValue(newstr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return newstr;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return (String) o;
    }
}
