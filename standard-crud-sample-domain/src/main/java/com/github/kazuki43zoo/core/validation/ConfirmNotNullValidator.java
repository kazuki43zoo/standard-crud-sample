package com.github.kazuki43zoo.core.validation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmNotNullValidator implements ConstraintValidator<ConfirmNotNull, Object> {

    private ConfirmNotNull constraint;
    private String confirmField;

    public void initialize(ConfirmNotNull constraint) {
        this.constraint = constraint;
        this.confirmField = "confirm" + StringUtils.capitalize(constraint.value());
    }

    public boolean isValid(Object bean, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(bean);
        Object fieldValue = beanWrapper.getPropertyValue(constraint.value());
        Object confirmFieldValue = beanWrapper.getPropertyValue(confirmField);
        if (fieldValue == null || confirmFieldValue != null) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(constraint.message())
                .addPropertyNode(confirmField).addConstraintViolation();
        return false;
    }
}
