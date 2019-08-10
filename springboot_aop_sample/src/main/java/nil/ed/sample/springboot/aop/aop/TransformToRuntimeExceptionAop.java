package nil.ed.sample.springboot.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;

/**
 * @author lidelin
 * @date 2019/08/01 11:32
 */
public class TransformToRuntimeExceptionAop {
    /**
     * 捕获异常之后重复抛出
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "@annotation(nil.ed.sample.springboot.aop.common.annotation.AopAroundLog)", throwing = "ex")
    public void throwingMethod(JoinPoint joinPoint, Exception ex) throws Throwable {
        throw new RuntimeException(ex);
    }
}
