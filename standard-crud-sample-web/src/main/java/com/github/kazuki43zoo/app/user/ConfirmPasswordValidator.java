package com.github.kazuki43zoo.app.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ConfirmPasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ConfirmPasswordContainer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ConfirmPasswordContainer confirmPasswordContainer = ConfirmPasswordContainer.class.cast(target);
        if (!errors.hasFieldErrors("password")
                && !errors.hasFieldErrors("confirmPassword")) {
            if (!confirmPasswordContainer.getPassword().equals(confirmPasswordContainer.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "e.sc.um.5007");
            }
        }
    }

}
