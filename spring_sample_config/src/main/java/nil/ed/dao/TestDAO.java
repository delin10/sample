package nil.ed.dao;

import nil.ed.entities.TestEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        Session session = null;
        //Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public TestEntity get(long id) {
        Session session = currentSession();
        String hql = "from TestEntity where id=:id";
        Query<TestEntity> query = session.createQuery(hql, TestEntity.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public void save(TestEntity entity) {
        Session session = currentSession();
        session.save(entity);
    }
}
