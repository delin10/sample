package nil.ed.springboot_servlet_sample.validator.annotation;

import nil.ed.springboot_servlet_sample.validator.RequestParamValidator;
import org.springframework.core.annotation.AliasFor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author lidelin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Constraint(validatedBy = RequestParamValidator.class)
public @interface Anno {
    String value() default "aa";

    String message() default "message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
