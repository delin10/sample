package org.delin.dao;

import org.delin.util.db.datasource.impl.JNDIDataSource;
import org.delin.util.db.template.IDBTemplate;
import org.delin.util.db.template.OrmTemplate;
import org.delin.util.db.template.convertor.IConverter;
import org.delin.util.db.template.jdbc.JDBCTemplateImpl;
import org.delin.util.db.template.jdbc.ResultsetParser;
import org.delin.util.db.template.jpa.JpaTemplateImpl;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public abstract class AbstractDAO<T> implements DAO<T> {
    private IDBTemplate template;
    private ResultsetParser<T> parser;
    private OrmTemplate ormTemplate;

    public AbstractDAO() {
        JNDIDataSource jndiDataSource = new JNDIDataSource("java:jboss/datasource/mysqlDS");
        template = new JDBCTemplateImpl(jndiDataSource);
        parser = new ResultsetParser<>(getClazz(), template);
        ormTemplate = new JpaTemplateImpl("book_jpa");
    }


    @Override
    public T get(Map<String, Object> ids) {
        ResultSet rs = template.executeUniqueQuery(ids, getTable());
        return (T) parser.parseObject(getClazz(), rs, true);
    }

    @Override
    public void add(T obj) {
        //template.save(obj, getTable());
        ormTemplate.add(obj);
    }

    @Override
    public void delete(Map<String, Object> ids) {
        template.delete(ids, getTable());
    }

    @Override
    public void update(Map<String, Object> updates, Map<String, Object> ids) {
        template.update(updates, ids, getTable());
    }

    @Override
    public List<T> listAll() {
        return parser.parseList(template.executeQuery("select * from " + getTable()));
    }

    @Override
    public void addAll(List<T> ls) {
        template.addBatchAndReturnFail(ls, getClazz(), getTable());
    }

    @Override
    public void addConverter(String field, IConverter converter) {
        template.addConverter(getClazz(), field, converter);
    }

    protected abstract String getTable();

    protected abstract Class<T> getClazz();
}
