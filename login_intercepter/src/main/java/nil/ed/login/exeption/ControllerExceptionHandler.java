package nil.ed.login.exeption;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * @author lidelin
 * @date 2019/07/29 18:31
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    // 声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defultExcepitonHandler(HttpServletRequest request, Exception e) {
        return MessageFormat.format("invoke {0};exception is {1}", "defultExcepitonHandler", e);
    }
}
