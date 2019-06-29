package org.delin.dao;

import org.delin.util.db.template.OrmTemplate;
import org.delin.util.db.template.jpa.JpaTemplateImpl;

import java.util.List;

public class AbstractJpaDAO<T> implements JpaDAO<T> {
    private OrmTemplate ormTemplate;

    public AbstractJpaDAO() {
        this.ormTemplate = new JpaTemplateImpl("book_jpa");
    }

    @Override
    public void add(T obj) {
        ormTemplate.add(obj);
    }

    @Override
    public void delete() {
    }

    @Override
    public void update(T obj) {

    }

    @Override
    public List<T> listAll() {
        return ormTemplate.listAll();
    }

    @Override
    public void addAll(List<T> ls) {
        ls.stream().forEach(ormTemplate::add);
    }
}
