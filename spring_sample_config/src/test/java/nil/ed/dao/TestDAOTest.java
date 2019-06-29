package nil.ed.dao;

import nil.ed.entities.TestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-root.xml"})
public class TestDAOTest {
    @Autowired
    private TestDAO dao;

    @Test
    public void testDAO() {
        TestEntity test = new TestEntity();
        test.setDesc("good");
        test.setName("delin");
        dao.save(test);
    }
}
