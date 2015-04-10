package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.domain.service.user.UserSharedService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;

import javax.inject.Inject;

@Component
public class UserIdValidator implements SmartValidator {

    @Inject
    UserSharedService userSharedService;

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

        if (errors.hasFieldErrors("userId") || errors.hasFieldErrors("userUuid")) {
            return;
        }

        boolean isValidUserId;
        if (isCreating) {
            isValidUserId = userSharedService.isValidUserIdOnCreating(form.getUserId());
        } else {
            isValidUserId = userSharedService.isValidUserIdOnUpdating(form.getUserId(), form.getUserUuid());
        }
        if (!isValidUserId) {
            userHelper.rejectInvalidUserId(errors);
        }

    }

}
