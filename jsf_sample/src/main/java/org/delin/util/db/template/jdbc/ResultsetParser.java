package org.delin.util.db.template.jdbc;

import org.delin.reflect.IReflector;
import org.delin.reflect.impl.JReflectorImpl;
import org.delin.util.db.template.IDBTemplate;
import org.delin.util.lambda.RuntimeExceptionExec;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ResultsetParser<T> {
    private IReflector reflector = new JReflectorImpl();
    private Class<T> clazz;
    private IDBTemplate template;

    public ResultsetParser(Class<T> clazz, IDBTemplate template) {
        this.clazz = clazz;
        this.template = template;
    }

    public List<T> parseList(ResultSet rs) {
        List<T> rls = new LinkedList<>();
        Object obj = null;
        while ((obj = parseObject(clazz, rs, false)) != null) {
            System.out.println(obj);
            rls.add((T) obj);
        }
        return rls;
    }

    public Object parseObject(Class<?> clazz, ResultSet rs, boolean close) {
        return RuntimeExceptionExec.exec(() -> {
            if (rs == null || !rs.next()) {
                return null;
            }
            Object obj = clazz.getConstructor().newInstance();
            Field[] fields = reflector.getAllField(clazz);
            System.out.println(obj);
            for (Field field : fields) {
                Class<?> cc = field.getType();
                String name = field.getName();
                Object value = rs.getObject(name);
                value = template.convertData(clazz, name, value, true);
                reflector.setFieldValue(obj, name, value);
//                if (cc.equals(String.class)) {
//                    System.out.println(name);
//                    boolean res = reflector.setFieldValue(obj, name, v);
//                } else if (cc.equals(Date.class)) {
//                    reflector.setFieldValue(obj, name, rs.getDate(name));
//                } else if (cc.isPrimitive()) {
//                    if (cc.equals(int.class)) {
//                        reflector.setFieldValue(obj, name, rs.getInt(name));
//                    } else if (cc.equals(float.class)) {
//                        reflector.setFieldValue(obj, name, rs.getFloat(name));
//                    }
//                }
            }
            if (close) {
                rs.close();
            }
            return obj;
        });
    }

}
