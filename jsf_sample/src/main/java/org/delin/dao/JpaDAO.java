package org.delin.dao;

import java.util.List;

public interface JpaDAO<T> {
    void add(T obj);

    void delete();

    void update(T obj);

    List<T> listAll();

    void addAll(List<T> ls);
}
