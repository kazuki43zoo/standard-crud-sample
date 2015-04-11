package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.app.ModelAttributeNames;
import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.service.security.CustomUserDetails;
import com.github.kazuki43zoo.domain.service.security.SecurityContextSharedService;
import com.github.kazuki43zoo.domain.service.user.UserService;
import org.dozer.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

import javax.inject.Inject;
import javax.validation.groups.Default;

@Controller
@RequestMapping("users")
@TransactionTokenCheck("users")
public class UserController {

    @Inject
    UserService userService;

    @Inject
    SecurityContextSharedService securityContextSharedService;

    @Inject
    UserHelper userHelper;

    @Inject
    UserIdValidator userIdValidator;

    @Inject
    ConfirmPasswordValidator confirmPasswordValidator;

    @Inject
    Mapper beanMapper;

    @ModelAttribute
    public UserForm setupUserForm() {
        return new UserForm();
    }

    @InitBinder("userForm")
    public void addValidators(WebDataBinder binder) {
        binder.addValidators(
                userIdValidator,
                confirmPasswordValidator);
    }

    @RequestMapping(method = RequestMethod.GET, params = "createForm")
    public String createForm() {
        return "user/createForm";
    }

    @RequestMapping(method = RequestMethod.POST, params = "createRedo")
    public String createRedo(UserForm userFrom) {
        return createForm();
    }

    @TransactionTokenCheck(value = "create", type = TransactionTokenType.BEGIN)
    @RequestMapping(method = RequestMethod.POST, params = "createConfirm")
    public String createConfirm(
            @Validated({Default.class, UserForm.Creating.class}) UserForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return createRedo(form);
        }

        return "user/createConfirm";
    }

    @TransactionTokenCheck(value = "create")
    @RequestMapping(method = RequestMethod.POST, params = "create")
    public String create(
            @Validated({Default.class, UserForm.Creating.class}) UserForm form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return createRedo(form);
        }

        User createdUser;
        try {
            User user = beanMapper.map(form, User.class);
            createdUser = userService.create(user, form.getRoles());
        } catch (DuplicateKeyException e) {
            userHelper.rejectInvalidUserId(bindingResult);
            return createRedo(form);
        }

        redirectAttributes.addAttribute("userUuid", createdUser.getUserUuid());
        return "redirect:/users/{userUuid}?createComplete";
    }

    @RequestMapping(value = "{userUuid}", method = RequestMethod.GET, params = "createComplete")
    public String createComplete(
            @PathVariable("userUuid") String userUuid,
            Model model) {
        userHelper.loadUserIntoModel(userUuid, model);
        return "user/createComplete";
    }

    @RequestMapping(value = "{userUuid}", method = RequestMethod.GET, params = "updateForm")
    public String updateForm(
            @PathVariable("userUuid") String userUuid,
            UserForm form,
            Model model) {
        User user = userHelper.loadUserIntoModel(userUuid, model);

        beanMapper.map(user, form);
        form.setPassword(null);
        user.getRoles().forEach(userRole -> {
            form.addRole(userRole.getRole());
        });
        model.addAttribute(form);

        return "user/updateForm";
    }

    @RequestMapping(value = "{userUuid}", method = RequestMethod.POST, params = "updateRedo")
    public String updateRedo(
            @PathVariable("userUuid") String userUuid,
            UserForm form, Model model) {
        userHelper.loadUserIntoModelWithinLongTransaction(userUuid, model, form);
        return "user/updateForm";
    }

    @TransactionTokenCheck(value = "update", type = TransactionTokenType.BEGIN)
    @RequestMapping(value = "{userUuid}", method = RequestMethod.POST, params = "updateConfirm")
    public String updateConfirm(
            @PathVariable("userUuid") String userUuid,
            @Validated({Default.class, UserForm.Updating.class}) UserForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return updateRedo(userUuid, form, model);
        }

        userHelper.loadUserIntoModelWithinLongTransaction(userUuid, model, form);

        return "user/updateConfirm";
    }

    @TransactionTokenCheck(value = "update")
    @RequestMapping(value = "{userUuid}", method = RequestMethod.POST, params = "update")
    public String update(
            @PathVariable("userUuid") String userUuid,
            @Validated({Default.class, UserForm.Updating.class}) UserForm form,
            BindingResult bindingResult,
            @AuthenticationPrincipal CustomUserDetails userDetail,
            @ModelAttribute(ModelAttributeNames.BACKWARD_QUERY_STRING) String backwardQueryString,
            Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return updateRedo(userUuid, form, model);
        }

        try {
            User user = beanMapper.map(form, User.class);
            userService.update(userUuid, user, form.getRoles());
            if (userDetail.getUser().getUserUuid().equals(userUuid)) {
                securityContextSharedService.updateSecurityContextByUserId(form.getUserId());
            }
        } catch (DuplicateKeyException e) {
            userHelper.rejectInvalidUserId(bindingResult);
            return updateRedo(userUuid, form, model);
        }

        redirectAttributes.addAttribute("userUuid", userUuid);
        redirectAttributes.addAttribute(ModelAttributeNames.BACKWARD_QUERY_STRING, backwardQueryString);
        return "redirect:/users/{userUuid}?updateComplete";
    }

    @RequestMapping(value = "{userUuid}", method = RequestMethod.GET, params = "updateComplete")
    public String updateComplete(
            @PathVariable("userUuid") String userUuid, Model model) {
        userHelper.loadUserIntoModel(userUuid, model);
        return "user/updateComplete";
    }


    @TransactionTokenCheck
    @RequestMapping(value = "{userUuid}", method = RequestMethod.POST, params = "delete")
    public String delete(
            @PathVariable("userUuid") String userUuid,
            @ModelAttribute(ModelAttributeNames.BACKWARD_QUERY_STRING) String backwardQueryString,
            RedirectAttributes redirectAttributes) {

        userService.delete(userUuid);

        redirectAttributes.addAttribute("userUuid", userUuid);
        redirectAttributes.addAttribute(ModelAttributeNames.BACKWARD_QUERY_STRING, backwardQueryString);
        return "redirect:/users/{userUuid}?deleteComplete";
    }

    @RequestMapping(value = "{userUuid}", method = RequestMethod.GET, params = "deleteComplete")
    public String deleteComplete(
            @PathVariable("userUuid") String userUuid, Model model) {
        userHelper.loadUserIntoModel(userUuid, model);
        return "user/deleteComplete";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView handleObjectOptimisticLockingFailureException(
            ObjectOptimisticLockingFailureException e) {
        ExtendedModelMap model = new ExtendedModelMap();
        String viewNameOfUpdateForm =
                updateForm(e.getIdentifier().toString(), new UserForm(), model);
        model.addAttribute(ResultMessages.danger().add("e.sc.um.8005"));
        userHelper.bindAllCodeListIntoModel(model);
        return new ModelAndView(viewNameOfUpdateForm, model);
    }

}
