package nil.ed.login.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lidelin
 * @date 2019/07/12 19:26
 */
public class AbstractHandlerInterceptor implements HandlerInterceptor {
    /**
     * 确定了匹配的Handler，在调用之前
     * @param request
     * @param response
     * @param handler
     * @return true -- 传递；false -- 终结于该拦截器
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return false;
    }

    /**
     * 调用handler后，渲染视图之前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(this+" -- postHandle");
    }

    /**
     * 视图渲染完成后
     * 只有preHandle返回true的那部分拦截器，
     * 部分完成也会调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(this+" -- afterCompletion");
    }
}
