package nil.ed.dao;

import nil.ed.BaseTest;
import nil.ed.entities.TestEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestDAOTest extends BaseTest {
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
