package nil.ed.sample.springboot.aop.aop;

import nil.ed.sample.springboot.aop.entity.TestEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lidelin
 * @date 2019/08/04 10:15
 */
@Aspect
@Component
public class TestAop {
    @Pointcut("execution(* nil.ed.sample.springboot.aop.service.impl.TestServiceImpl.*(*))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public List<TestEntity> aroundListTestEntities(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("execution aroundListTestEntities");
        try {
            return (List<TestEntity>) jp.proceed();
        } catch (Exception e) {
            throw e;
            //return null;
        }
    }

//    @AfterThrowing(value = "pointcut()", throwing = "throwable")
//    public void afterThrowing(JoinPoint jp, Throwable throwable) {
//        System.out.println("afterThrowing");
//    }
}
