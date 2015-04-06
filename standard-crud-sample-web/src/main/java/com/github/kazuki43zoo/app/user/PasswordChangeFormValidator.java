package com.github.kazuki43zoo.app.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordChangeFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordChangeForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordChangeForm form = PasswordChangeForm.class.cast(target);
        validatePasswordAndNewPassword(form, errors);
        validateNewPasswordAndConfirmNewPassword(form, errors);
    }

    private void validatePasswordAndNewPassword(PasswordChangeForm form, Errors errors) {
        if (!errors.hasFieldErrors("password")
                && !errors.hasFieldErrors("newPassword")) {
            if (form.getPassword().equals(form.getNewPassword())) {
                errors.rejectValue("newPassword", "e.sc.um.5011");
            }
        }
    }

    private void validateNewPasswordAndConfirmNewPassword(PasswordChangeForm form, Errors errors) {
        if (!errors.hasFieldErrors("newPassword")
                && !errors.hasFieldErrors("confirmNewPassword")) {
            if (!form.getNewPassword().equals(form.getConfirmNewPassword())) {
                errors.rejectValue("confirmNewPassword", "e.sc.um.5007");
            }
        }
    }

}
