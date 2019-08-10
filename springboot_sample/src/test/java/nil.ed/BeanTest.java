package nil.ed;

import nil.ed.test.bean.TestBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lidelin
 * @date 2019/08/05 10:47
 */
public class BeanTest extends AbstractServiceTest {

    @Autowired
    private TestBean testBean;

    @Test
    public void test() {
        System.out.println(testBean.getA());
    }
}
