package nil.ed.config;

import nil.ed.BaseTest;
import nil.ed.entities.TestEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("jdbc")
public class TestOrmIntegrationConfig extends BaseTest {
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testHibernate() {
        Session session=sessionFactory.openSession();
        session.createQuery("from TestEntity", TestEntity.class);
    }
}
