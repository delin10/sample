package nil.ed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.support.StandardServletEnvironment;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:spring-jndi.properties", "classpath:jdbc.properties"})
public class DataSourceConfig {
    @Autowired
    private StandardServletEnvironment environment;

    @Profile("jndi")
    @Bean("dataSourcex")
    public JndiObjectFactoryBean jndiDataSource() {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/mysql");
        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
        return jndiObjectFactoryBean;
    }

    @Profile("jdbc")
    @Bean("dataSourcex")
    public DataSource jdbcDataSource() {
        //StandardServletEnvironment
        //System.out.println("................"+environment);
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty("jdbc.driver_class"));
        driverManagerDataSource.setUrl(environment.getProperty("jdbc.url"));
        driverManagerDataSource.setUsername(environment.getProperty("jdbc.username"));
        driverManagerDataSource.setPassword(environment.getProperty("jdbc.password"));
        return driverManagerDataSource;
    }
}
