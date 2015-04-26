package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.app.flow.DefaultFlow;
import com.github.kazuki43zoo.app.flow.Flow;
import com.github.kazuki43zoo.app.flow.FlowHelper;
import com.github.kazuki43zoo.core.message.Message;
import com.github.kazuki43zoo.domain.model.StreetAddress;
import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.service.security.CustomUserDetails;
import com.github.kazuki43zoo.domain.service.security.SecurityContextSharedService;
import com.github.kazuki43zoo.domain.service.user.ProfileService;
import org.dozer.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    FlowHelper flowHelper;

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

    @RequestMapping(method = RequestMethod.POST, params = "reload")
    public String reloadForm(
            @AuthenticationPrincipal CustomUserDetails userDetail,
            ProfileForm form) {
        return editForm(userDetail, form);
    }

    @RequestMapping(method = RequestMethod.POST, params = "gotoAddressSearch")
    public String gotoAddressSearch(
            ProfileForm form,
            Model model,
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            RedirectAttributes redirectAttributes) {
        currentFlow.saveModel(model);
        Flow newFlow = DefaultFlow.builder(currentFlow)
                .finishPath("/profile?applyAddress")
                .cancelPath("/profile?redo")
                .build();
        return flowHelper.redirectAndBeginFlow(
                "/share/streetAddresses?searchForm",
                newFlow,
                redirectAttributes);
    }

    @RequestMapping(method = RequestMethod.GET, params = "applyAddress")
    public String applyAddress(
            ProfileForm form,
            StreetAddress selectedStreetAddress) {
        form.setAddress(selectedStreetAddress.getAddress());
        return "user/profileEditForm";
    }

    @RequestMapping(params = "redo")
    public String editRedo(ProfileForm form) {
        return "user/profileEditForm";
    }

    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    @RequestMapping(method = RequestMethod.POST, params = "confirm")
    public String editConfirm(
            @Validated({Default.class, ProfileForm.Updating.class}) ProfileForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(Message.VALIDATION_ERROR.resultMessages());
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
            Model model,
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(Message.VALIDATION_ERROR.resultMessages());
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
            redirectAttributes.addFlashAttribute(Message.OPERATION_CONFLICT.resultMessages());
            return "redirect:/profile";
        }

        redirectAttributes.addAllAttributes(currentFlow.asIdMap());
        return "redirect:/profile?complete";
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String updateComplete() {
        return "user/profileEditComplete";
    }

}
