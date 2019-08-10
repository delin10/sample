package nil.ed.test.config;

import nil.ed.test.bean.TestBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @author lidelin
 * @date 2019/08/05 10:36
 */
@Configuration
public class BeanConfig {
    /**
     * 懒加载
     * @return
     */
    @Lazy
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean(initMethod = "init",
            destroyMethod = "close")
    public TestBean testBean() {
        return new TestBean();
    }
}
