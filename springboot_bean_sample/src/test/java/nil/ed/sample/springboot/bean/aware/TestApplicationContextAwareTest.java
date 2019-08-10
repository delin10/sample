package nil.ed.sample.springboot.bean.aware;

import nil.ed.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

import java.util.Arrays;

/**
 * @author lidelin
 * @date 2019/07/29 16:24
 */
public class TestApplicationContextAwareTest extends AbstractServiceTest {
    @Autowired
    private TestApplicationContextAware applicationContextAware;

    @Test
    public void test() {
        ApplicationContext currentContext = applicationContextAware.getContext();
        ApplicationContext parentContext = currentContext.getParent();
        System.out.println(parentContext);
        String[] names = applicationContextAware.getContext().getBeanDefinitionNames();
        System.out.println(applicationContextAware.getEnvironment());
        Arrays.stream(names).forEach(System.out::println);
    }
}
