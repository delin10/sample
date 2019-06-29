package org.delin.util.web;


import org.delin.reflect.IReflector;
import org.delin.reflect.impl.JReflectorImpl;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

public class RequestParser {
    private IReflector reflector;

    public RequestParser() {
        this.reflector = new JReflectorImpl();
    }

    public Object parseBody(HttpServletRequest request, Class<?> clazz) {
        Field[] fields = reflector.getAllField(clazz);
        Object obj = null;
        try {
            obj = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (Field field : fields) {
            String value = request.getParameter(field.getName());
            reflector.setFieldValue(obj, field.getName(), value);
        }
        return obj;
    }
}
