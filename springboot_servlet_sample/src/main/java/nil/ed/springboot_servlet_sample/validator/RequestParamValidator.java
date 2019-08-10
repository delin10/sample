package nil.ed.springboot_servlet_sample.validator;

import nil.ed.springboot_servlet_sample.validator.annotation.Anno;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.swing.*;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validator;

/**
 * @author lidelin
 * @date 2019/08/01 17:24
 */
public class RequestParamValidator implements ConstraintValidator<Anno, Object> {

    @Override
    public void initialize(Anno constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println(value);
        return true;
    }
}
