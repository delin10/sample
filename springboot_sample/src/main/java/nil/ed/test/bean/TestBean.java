package nil.ed.test.bean;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author lidelin
 * @date 2019/08/05 10:22
 */
@Data
public class TestBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, DisposableBean, BeanPostProcessor, InitializingBean {
    /**
     * 在实例化后进行值的注入
     */
    @Value("${a:99999}")
    private int a;
    private int count;

    public TestBean() {
        System.out.println("constructor " + count++);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext" + count++);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory" + count++);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName" + count++);
    }

    @Override
    public void destroy() {
        System.out.println("destroy" + count++);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization"
                + (count++)
                + "bean is "
                + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization"
                + (count++)
                + "bean is "
                + bean);
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet"
                + (count++));
    }

    public void init() {
        System.out.println("init" + (count++));
        this.a = 1000000;
    }

    public void close() {
        System.out.println("close" + (count++));
    }
}
