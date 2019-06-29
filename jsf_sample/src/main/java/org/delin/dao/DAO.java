package org.delin.dao;

import org.delin.util.db.template.convertor.IConverter;

import java.util.List;
import java.util.Map;

public interface DAO<T> {
    T get(Map<String, Object> ids);

    void add(T obj);

    void delete(Map<String, Object> ids);

    void update(Map<String, Object> updates, Map<String, Object> ids);

    List<T> listAll();

    void addAll(List<T> ls);

    void addConverter(String field,IConverter converter);
}
