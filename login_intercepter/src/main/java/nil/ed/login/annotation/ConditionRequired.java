package nil.ed.login.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lidelin
 * @date 2019/07/12 19:43
 */
//没设置无法获取注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ConditionRequired {
    boolean requireLogin() default false;
    boolean requireRegister() default false;
}
