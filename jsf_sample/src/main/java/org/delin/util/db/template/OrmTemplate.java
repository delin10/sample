package org.delin.util.db.template;

import java.util.List;

public interface OrmTemplate {
    Object get(String id, Class<?> clazz);

    List listAll();

    void add(Object obj);

}
