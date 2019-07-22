package nil.ed.login.config;

import nil.ed.login.interceptor.AnnotationConditionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 配置拦截器
 *
 * @author lidelin
 * @date 2019/07/12 19:49
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Bean
    public AnnotationConditionInterceptor annotationConditionInterceptor() {
        return new AnnotationConditionInterceptor();
    }

    //解决中文乱码问题
    //有个锤子用
//    @Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        return converter;
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        converters.add(responseBodyConverter());
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(annotationConditionInterceptor()).addPathPatterns("/test/*").excludePathPatterns("/test/testC");
    }
}
