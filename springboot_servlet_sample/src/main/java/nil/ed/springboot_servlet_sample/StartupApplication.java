package nil.ed.springboot_servlet_sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * SpringBoot启动类
 * @author lidelin 
 * @since 2019/07/22 15:43:31
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "nil.ed.springboot_servlet_sample.servlet")
public class StartupApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartupApplication.class, args);
    }
}
