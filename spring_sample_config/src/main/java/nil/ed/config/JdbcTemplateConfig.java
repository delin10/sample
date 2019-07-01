package nil.ed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {
    @Bean
    public JdbcTemplate jndiTemplate(DataSource dataSourcex) {
        return new JdbcTemplate(dataSourcex);
    }

}
