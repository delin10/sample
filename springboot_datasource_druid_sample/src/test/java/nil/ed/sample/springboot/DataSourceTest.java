package nil.ed.sample.springboot;

import nil.ed.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author lidelin
 * @date 2019/07/22 17:46
 */
public class DataSourceTest extends AbstractServiceTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    public void test() {
        jdbcTemplate.queryForList("select * from test");
    }
}
