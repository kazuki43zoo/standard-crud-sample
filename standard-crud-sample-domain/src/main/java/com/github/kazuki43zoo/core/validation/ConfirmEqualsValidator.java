package com.github.kazuki43zoo.core.validation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ConfirmEqualsValidator implements ConstraintValidator<ConfirmEquals, Object> {

    private ConfirmEquals constraint;
    private String confirmField;

    public void initialize(ConfirmEquals constraint) {
        this.constraint = constraint;
        this.confirmField = "confirm" + StringUtils.capitalize(constraint.value());
    }

    public boolean isValid(Object bean, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(bean);
        Object fieldValue = beanWrapper.getPropertyValue(constraint.value());
        Object confirmFieldValue = beanWrapper.getPropertyValue(confirmField);
        if (fieldValue == null || confirmFieldValue == null) {
            return true;
        }
        if (Objects.equals(fieldValue, confirmFieldValue)) {
            return true;
        }
        System.out.println(context.getDefaultConstraintMessageTemplate());
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(constraint.message())
                .addPropertyNode(confirmField).addConstraintViolation();
        return false;
    }
}
