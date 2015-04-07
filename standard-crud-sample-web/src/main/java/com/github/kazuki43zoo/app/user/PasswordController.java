package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.domain.service.user.CredentialService;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

import javax.inject.Inject;

@Controller
@RequestMapping("password")
@TransactionTokenCheck("password")
public class PasswordController {

    @Inject
    CredentialService credentialService;

    @Inject
    PasswordFormValidator passwordChangeFormValidator;

    @ModelAttribute
    public PasswordForm setupPasswordForm() {
        return new PasswordForm();
    }

    @InitBinder("passwordForm")
    public void addValidators(WebDataBinder binder) {
        binder.addValidators(passwordChangeFormValidator);
    }

    @TransactionTokenCheck(value = "change", type = TransactionTokenType.BEGIN)
    @RequestMapping(method = RequestMethod.GET, params = "changeForm")
    public String changeForm() {
        return "user/passwordChangeForm";
    }

    @TransactionTokenCheck(value = "change")
    @RequestMapping(method = RequestMethod.POST)
    public String change(
            @Validated PasswordForm form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return changeForm();
        }

        try {

            credentialService.change(form.getUserId(), form.getPassword(), form.getNewPassword());

        } catch (BusinessException e) {
            redirectAttributes.addFlashAttribute(e.getResultMessages());
            redirectAttributes.addFlashAttribute(form);
            return "redirect:/password?changeForm";

        } catch (ObjectOptimisticLockingFailureException e) {
            redirectAttributes.addFlashAttribute(ResultMessages.error().add("e.sc.um.8005"));
            redirectAttributes.addFlashAttribute(form);
            return "redirect:/password?changeForm";

        }

        redirectAttributes.addFlashAttribute(ResultMessages.success().add("i.sc.um.1010"));
        return "redirect:/login";
    }

}
