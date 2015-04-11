package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.service.security.CustomUserDetails;
import com.github.kazuki43zoo.domain.service.security.SecurityContextSharedService;
import com.github.kazuki43zoo.domain.service.user.ProfileService;
import org.dozer.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

import javax.inject.Inject;
import javax.validation.groups.Default;

@Controller
@RequestMapping("profile")
@TransactionTokenCheck("profile")
public class ProfileController {

    @Inject
    ProfileService profileService;

    @Inject
    SecurityContextSharedService securityContextSharedService;

    @Inject
    UserHelper userHelper;

    @Inject
    Mapper beanMapper;

    @ModelAttribute
    public ProfileForm setupProfileForm() {
        return new ProfileForm();
    }

    @ModelAttribute
    public User setupUser(@AuthenticationPrincipal CustomUserDetails userDetail) {
        return userDetail.getUser();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String editForm(
            @AuthenticationPrincipal CustomUserDetails userDetail,
            ProfileForm form) {
        beanMapper.map(userDetail.getUser(), form);
        form.setPassword(null);
        return "user/profileEditForm";
    }

    @RequestMapping(method = RequestMethod.POST, params = "redo")
    public String editRedo(ProfileForm form) {
        return "user/profileEditForm";
    }

    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    @RequestMapping(method = RequestMethod.POST, params = "confirm")
    public String editConfirm(
            @Validated({Default.class, ProfileForm.Updating.class}) ProfileForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return editRedo(form);
        }

        return "user/profileEditConfirm";
    }

    @TransactionTokenCheck
    @RequestMapping(method = RequestMethod.POST)
    public String edit(
            @AuthenticationPrincipal CustomUserDetails userDetail,
            @Validated({Default.class, ProfileForm.Updating.class}) ProfileForm form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return editRedo(form);
        }

        try {
            User inputUser = beanMapper.map(form, User.class);
            profileService.edit(userDetail.getUser().getUserUuid(), inputUser);
            securityContextSharedService.updateSecurityContextByUserId(form.getUserId());
        } catch (DuplicateKeyException e) {
            userHelper.rejectInvalidUserId(bindingResult);
            return editRedo(form);
        } catch (ObjectOptimisticLockingFailureException e) {
            securityContextSharedService.updateSecurityContextByUserUuid(userDetail.getUser().getUserUuid());
            redirectAttributes.addFlashAttribute(ResultMessages.danger().add("e.sc.um.8005"));
            return "redirect:/profile";
        }

        return "redirect:/profile?complete";
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String updateComplete() {
        return "user/profileEditComplete";
    }

}
