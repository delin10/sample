package nil.ed.sample.springboot.bean.aware;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author lidelin
 * @date 2019/07/29 16:22
 */
@Component
public class TestApplicationContextAware implements ApplicationContextAware, EnvironmentAware, EnvironmentCapable {
    private ApplicationContext context;
    private Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public ApplicationContext getContext() {

        return context;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }
}
