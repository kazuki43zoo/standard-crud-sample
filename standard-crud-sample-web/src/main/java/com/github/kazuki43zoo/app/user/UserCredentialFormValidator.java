package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.domain.service.user.UserCredentialShardService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;

import javax.inject.Inject;

@Component
public class UserCredentialFormValidator implements SmartValidator {

    @Inject
    UserCredentialShardService userCredentialShardService;

    @Inject
    UserHelper userHelper;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCredentialForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        throw new UnsupportedOperationException("validate(Object, Errors) is not supported.");
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {

        boolean isCreating = ArrayUtils.contains(validationHints, UserCredentialForm.Creating.class);
        UserCredentialForm form = UserCredentialForm.class.cast(target);

        validateUserId(form, errors, isCreating);

        validatePasswordAndConfirmationPassword(form, errors);

    }

    private void validateUserId(UserCredentialForm form, Errors errors, boolean isCreating) {
        if (!errors.hasFieldErrors("userId")) {
            boolean isValidUserId;
            if (isCreating) {
                isValidUserId = userCredentialShardService.isValidUserIdOnCreating(form.getUserId());
            } else {
                isValidUserId = userCredentialShardService.isValidUserIdOnUpdating(form.getUserId(), form.getUserUuid());
            }
            if (!isValidUserId) {
                userHelper.rejectInvalidUserId(errors);
            }
        }
    }

    private void validatePasswordAndConfirmationPassword(UserCredentialForm form, Errors errors) {
        if (!errors.hasFieldErrors("password")
                && !errors.hasFieldErrors("confirmPassword")) {
            if (form.getPassword() != null) {
                if (!form.getPassword().equals(form.getConfirmPassword())) {
                    errors.rejectValue("confirmPassword", "e.sc.um.5007");
                }
            }
        }
    }

}
