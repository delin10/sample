package nil.ed.login.interceptor;

import nil.ed.login.annotation.ConditionRequired;
import nil.ed.login.aware.ApplicationContextAwareImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author lidelin
 * @date 2019/07/12 19:24
 */
public class AnnotationConditionInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AnnotationConditionInterceptor.class);
    @Autowired
    private ApplicationContextAwareImpl applicationContextAware;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("拦截...");
        //如果不是映射到方法直接通过？？
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }else if(true){
            throw new RuntimeException("抛出...");
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //handlerMethod无法获取注释
        Method method = handlerMethod.getMethod();
        ConditionRequired conditionRequiredAnnotation = method.getAnnotation(ConditionRequired.class);

        logger.info("注释{}",conditionRequiredAnnotation);
        //包含注释需要在下面if块中解析
        if (conditionRequiredAnnotation != null) {
            if (conditionRequiredAnnotation.requireRegister()) {
                response.getWriter().println("需要注册");
                return false;
            }
            if (conditionRequiredAnnotation.requireLogin()) {
                response.getWriter().println("需要注册");
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle..");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
