package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.app.flow.DefaultFlow;
import com.github.kazuki43zoo.app.flow.Flow;
import com.github.kazuki43zoo.app.flow.FlowManager;
import com.github.kazuki43zoo.core.message.Message;
import com.github.kazuki43zoo.domain.model.StreetAddress;
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
    FlowManager flowManager;

    @Inject
    Mapper beanMapper;

    @ModelAttribute
    public UserForm setupUserForm() {
        return new UserForm();
    }

    @InitBinder("userForm")
    public void addValidators(WebDataBinder binder) {
        binder.addValidators(userIdValidator);
    }

    @RequestMapping(params = "createForm")
    public String createForm() {
        return "user/createForm";
    }

    @RequestMapping(params = "clearCreateForm")
    public String clearCreateForm(Model model) {
        model.addAttribute(setupUserForm());
        return createForm();
    }

    @RequestMapping(method = RequestMethod.POST, params = "gotoAddressSearch")
    public String gotoAddressSearch(
            UserForm form,
            Model model,
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            RedirectAttributes redirectAttributes) {
        currentFlow.saveModel(model);
        Flow newFlow = DefaultFlow.builder(currentFlow)
                .finishPath("/users?applyAddress&destination=createForm")
                .cancelPath("/users?createRedo")
                .build();
        return flowManager.redirectAndBeginFlow(
                "/share/streetAddresses?searchForm",
                newFlow,
                redirectAttributes);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"applyAddress", "destination=createForm"})
    public String applyAddress(
            UserForm form,
            StreetAddress selectedStreetAddress) {
        form.setAddress(selectedStreetAddress.getAddress());
        return "user/createForm";
    }

    @RequestMapping(params = "createRedo")
    public String createRedo(UserForm userFrom) {
        return createForm();
    }

    @TransactionTokenCheck(value = "create", type = TransactionTokenType.BEGIN)
    @RequestMapping(method = RequestMethod.POST, params = "createConfirm")
    public String createConfirm(
            @Validated({Default.class, UserForm.Creating.class}) UserForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(Message.VALIDATION_ERROR.resultMessages());
            return createRedo(form);
        }

        return "user/createConfirm";
    }

    @TransactionTokenCheck(value = "create")
    @RequestMapping(method = RequestMethod.POST, params = "create")
    public String create(
            @Validated({Default.class, UserForm.Creating.class}) UserForm form,
            BindingResult bindingResult,
            Model model,
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(Message.VALIDATION_ERROR.resultMessages());
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
        redirectAttributes.addAllAttributes(currentFlow.asIdMap());
        return "redirect:/users/{userUuid}?createComplete";
    }

    @RequestMapping(value = "{userUuid}", method = RequestMethod.GET, params = "createComplete")
    public String createComplete(
            @PathVariable("userUuid") String userUuid,
            Model model) {
        userHelper.loadUserIntoModel(userUuid, model);
        return "user/createComplete";
    }

    @RequestMapping(value = "{userUuid}", params = "updateForm")
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

    @RequestMapping(value = "{userUuid}", params = "reloadUpdateForm")
    public String clearUpdateForm(@PathVariable("userUuid") String userUuid, UserForm form, Model model) {
        return updateForm(userUuid, form, model);
    }

    @RequestMapping(value = "{userUuid}", method = RequestMethod.POST, params = "gotoAddressSearch")
    public String gotoAddressSearch(
            @PathVariable("userUuid") String userUuid,
            UserForm form,
            Model model,
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            RedirectAttributes redirectAttributes) {
        currentFlow.saveModel(model);
        Flow newFlow = DefaultFlow.builder(currentFlow)
                .finishPath("/users/" + userUuid + "?applyAddress")
                .cancelPath("/users/" + userUuid + "?updateRedo")
                .build();
        return flowManager.redirectAndBeginFlow(
                "/share/streetAddresses?searchForm",
                newFlow,
                redirectAttributes);
    }

    @RequestMapping(value = "{userUuid}", method = RequestMethod.GET, params = "applyAddress")
    public String applyAddress(
            @PathVariable("userUuid") String userUuid,
            UserForm form,
            StreetAddress selectedStreetAddress,
            Model model) {
        form.setAddress(selectedStreetAddress.getAddress());
        userHelper.loadUserIntoModelWithinLongTransaction(userUuid, model, form);
        return "user/updateForm";
    }

    @RequestMapping(value = "{userUuid}", params = "updateRedo")
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
            model.addAttribute(Message.VALIDATION_ERROR.resultMessages());
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
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(Message.VALIDATION_ERROR.resultMessages());
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
        redirectAttributes.addAllAttributes(currentFlow.asIdMap());
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
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            RedirectAttributes redirectAttributes) {

        userService.delete(userUuid);

        redirectAttributes.addAttribute("userUuid", userUuid);
        redirectAttributes.addAllAttributes(currentFlow.asIdMap());
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
        model.addAttribute(Message.OPERATION_CONFLICT.resultMessages());
        userHelper.bindAllCodeListIntoModel(model);
        return new ModelAndView(viewNameOfUpdateForm, model);
    }

}
