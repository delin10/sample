package nil.ed.sample.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lidelin
 * @date 2019/07/22 17:32
 */

/**
 * 加这个注释自动注入
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DataSourceConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int initialSize;
    private long maxWait;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private String connectionProperties;
    private boolean useGlobalDataSourceStat;

    @Primary
    @Bean
    public DataSource druidDataSource() throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        // 配置基本属性
        druid.setDriverClassName(driverClassName);
        druid.setUsername(username);
        druid.setPassword(password);
        druid.setUrl(url);
        System.out.println(url);

        // 初始化时建立物理连接的个数
        druid.setInitialSize(initialSize);
        // 最大连接池数量
        druid.setConnectionProperties(connectionProperties);
        // 最小连接池数量
        druid.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        // 获取连接时最大等待时间，单位毫秒。
        druid.setMaxWait(maxWait);
        // 间隔多久进行一次检测，检测需要关闭的空闲连接
        druid.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 一个连接在池中最小生存的时间
        druid.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 用来检测连接是否有效的sql
        druid.setValidationQuery(validationQuery);
        // 建议配置为true，不影响性能，并且保证安全性。
        druid.setTestWhileIdle(testWhileIdle);
        // 申请连接时执行validationQuery检测连接是否有效
        druid.setTestOnBorrow(testOnBorrow);
        druid.setTestOnReturn(testOnReturn);
        // 是否缓存preparedStatement，也就是PSCache，oracle设为true，mysql设为false。分库分表较多推荐设置为false
        druid.setPoolPreparedStatements(poolPreparedStatements);
        List<String> connectionInitSqls;
        connectionInitSqls = new ArrayList<String>();
        connectionInitSqls.add("set names utf8mb4;");
        druid.setConnectionInitSqls(connectionInitSqls);
        return druid;
    }

    public JdbcTemplate jdbcTemplate()throws SQLException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(druidDataSource());
        return jdbcTemplate;
    }
}
