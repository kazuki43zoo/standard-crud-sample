package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.domain.service.user.CredentialSharedService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;

import javax.inject.Inject;

@Component
public class ProfileFormValidator implements SmartValidator {

    @Inject
    CredentialSharedService credentialShardService;

    @Inject
    UserHelper userHelper;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        throw new UnsupportedOperationException("validate(Object, Errors) is not supported.");
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {

        boolean isCreating = ArrayUtils.contains(validationHints, ProfileForm.Creating.class);
        ProfileForm form = ProfileForm.class.cast(target);

        validateUserId(form, errors, isCreating);

        validatePasswordAndConfirmPassword(form, errors);

    }

    private void validateUserId(ProfileForm form, Errors errors, boolean isCreating) {
        if (!errors.hasFieldErrors("userId")) {
            boolean isValidUserId;
            if (isCreating) {
                isValidUserId = credentialShardService.isValidUserIdOnCreating(form.getUserId());
            } else {
                isValidUserId = credentialShardService.isValidUserIdOnUpdating(form.getUserId(), form.getUserUuid());
            }
            if (!isValidUserId) {
                userHelper.rejectInvalidUserId(errors);
            }
        }
    }

    private void validatePasswordAndConfirmPassword(ProfileForm form, Errors errors) {
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
