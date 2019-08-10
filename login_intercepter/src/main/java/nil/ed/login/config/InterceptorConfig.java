package nil.ed.login.config;

import nil.ed.login.interceptor.AnnotationConditionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 配置拦截器
 *
 * @Deprecated 导致Springboot自动配置失效
 * @author lidelin
 * @date 2019/07/12 19:49
 */

/**
 * @Import(MessageConverterConfig.class)
 * 不会调用WebMvcConfigurationSupport
 */
@Deprecated
public class InterceptorConfig extends WebMvcConfigurationSupport {

}
