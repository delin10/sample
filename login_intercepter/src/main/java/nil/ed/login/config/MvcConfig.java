package nil.ed.login.config;

import nil.ed.login.interceptor.AnnotationConditionInterceptor;
import nil.ed.login.interceptor.FirstTestInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author lidelin
 * @date 2019/07/30 11:18
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public AnnotationConditionInterceptor annotationConditionInterceptor() {
        return new AnnotationConditionInterceptor();
    }

    @Bean
    public FirstTestInterceptor firstTestInterceptor() {
        return new FirstTestInterceptor();
    }


    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(firstTestInterceptor()).addPathPatterns("/test/*");
        registry.addInterceptor(annotationConditionInterceptor()).addPathPatterns("/test/*");
    }

    /**
     * 解决???乱码问题
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
    }
}
