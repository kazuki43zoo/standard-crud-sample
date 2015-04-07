package com.github.kazuki43zoo.app.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordForm form = PasswordForm.class.cast(target);
        if (!errors.hasFieldErrors("currentPassword")
                && !errors.hasFieldErrors("password")) {
            if (form.getCurrentPassword().equals(form.getPassword())) {
                errors.rejectValue("password", "e.sc.um.5011");
            }
        }
    }

}
