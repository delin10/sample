package nil.ed.sample.springboot.aop.aop;

import lombok.extern.slf4j.Slf4j;
import nil.ed.sample.springboot.aop.entity.TestEntity;
import nil.ed.sample.springboot.aop.util.AspectJUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.UndeclaredThrowableException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Springboot 不需要@EnableAspectJAutoProxy
 *
 * @author lidelin
 * @date 2019/07/29 10:23
 */
@Slf4j
@Aspect
@Component
public class LoggerAop {

    @Pointcut("@annotation(nil.ed.sample.springboot.aop.common.annotation.AopAroundLog)")
    public void pointcut() {

    }

    /**
     * 环绕方法测试
     *
     * @param joinPoint
     */
    @Around("pointcut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        // 获取签名参数名
        String paramInfos = AspectJUtils.getParametersAndValuesMap(joinPoint).entrySet().stream()
                .map(entry -> MessageFormat.format("{0} = {1}", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(","));
        String defClass = AspectJUtils.getMethodDefineClass(joinPoint).getSimpleName();
        String fullPathMethodName = MessageFormat.format("{0}.{1}", defClass, signature.getName());
        log.info("Start to invoke {}, entry params: {}", fullPathMethodName, paramInfos);
        Object returnResult = null;
        Map<String, Object> map;
        try {
            returnResult = joinPoint.proceed();
            log.info("Success to invoke {}! result: {}", fullPathMethodName, returnResult);
        } catch (Exception e) {
            log.error("Failed to invoke {},error message as following:", fullPathMethodName, e);
            //throw new Exception();
        } catch (Throwable throwable) {
            log.error("Failed to invoke {},error message as following:", fullPathMethodName, throwable);
        } finally {
            return returnResult;
        }
    }

    @Before("pointcut()")
    public void beforeMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        // 获取签名参数名
        String paramInfos = AspectJUtils.getParametersAndValuesMap(joinPoint).entrySet().stream()
                .map(entry -> MessageFormat.format("{0} = {1}", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(","));
        String defClass = AspectJUtils.getMethodDefineClass(joinPoint).getSimpleName();
        String fullPathMethodName = MessageFormat.format("{0}.{1}", defClass, signature.getName());
        log.info("{} Start to invoke {}, entry params: {}", "beforeMethod", fullPathMethodName, paramInfos);
    }

    /**
     * ProceedingJoinPoint is only supported for around advice
     */
    @After("pointcut()")
    public void afterMethod(JoinPoint joinPoint) {
        log.info("{} Start to invoke , entry params: ", "afterMethod");
    }

    /**
     * ProceedingJoinPoint is only supported for around advice
     */
    @AfterReturning(value = "pointcut()", returning = "ls")
    public void afterMethod(JoinPoint joinPoint, List<TestEntity> ls) {
        System.out.println("execution afterMethod");
        log.info("{} Start to invoke , entry params: ", "afterMethod");
        System.out.println(ls);

    }


    /**
     * 捕获异常之后重复抛出
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "@annotation(nil.ed.sample.springboot.aop.common.annotation.AopAroundLog)", throwing = "ex")
    public void throwingMethod(JoinPoint joinPoint, Exception ex) throws Throwable {
        log.info("{} throwing ", "throwingMethod", ex);
    }
}
