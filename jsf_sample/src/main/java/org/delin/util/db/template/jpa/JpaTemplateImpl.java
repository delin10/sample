package org.delin.util.db.template.jpa;

import org.delin.entities.BookEntity;
import org.delin.util.db.template.OrmTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JpaTemplateImpl implements OrmTemplate {
    private String unitName;
    private EntityManager entityManager;

    public JpaTemplateImpl(String unitName) {
        this.unitName = unitName;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public Object get(String id, Class<?> clazz) {
        return entityManager.find(clazz, id);
    }

    @Override
    public List listAll() {
        return entityManager.createQuery("select book from BookEntity book").getResultList();
    }

    @Override
    public void add(Object obj) {
        entityManager.refresh(obj);
        //entityManager.persist(obj);
    }
}
