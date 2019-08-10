package nil.ed.sample.springboot.aop.common.annotation;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AopAroundLog {
}
