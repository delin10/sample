package nil.ed.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lidelin
 * @date 2019/07/31 15:08
 */
public class FirstTestInterceptor extends AbstractHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // afterCompletion 成功调用
        return true;
    }
}
