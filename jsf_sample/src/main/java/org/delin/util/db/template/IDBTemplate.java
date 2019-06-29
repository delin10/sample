package org.delin.util.db.template;

import org.delin.util.db.template.convertor.IConverter;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface IDBTemplate {
    ResultSet executeQuery(String sql);

    ResultSet executeUniqueQuery(Map<String, Object> ids, String table);

    List executeForResult(String sql);

    void save(Object obj, String table);


    <T> List<T> addBatchAndReturnFail(List<T> ls, Class<?> clazz, String table);

    void update(Map<String, Object> updates, Map<String, Object> ids, String table);

    void delete(Map<String, Object> ids, String table);

    void addConverter(Class<?> clazz, String field, IConverter converter);

    Object convertData(Class<?> clazz, String field, Object value, boolean fromDataBase);
}
