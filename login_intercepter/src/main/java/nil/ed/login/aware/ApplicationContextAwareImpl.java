package nil.ed.login.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author lidelin
 * @date 2019/07/30 09:45
 */
@Component
public class ApplicationContextAwareImpl implements ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void printContextBeans() {
        String[] defBeans = context.getBeanDefinitionNames();
        Arrays.stream(defBeans).forEach(System.out::println);
    }
}
