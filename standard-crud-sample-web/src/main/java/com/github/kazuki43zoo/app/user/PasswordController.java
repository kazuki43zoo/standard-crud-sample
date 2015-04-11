package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.domain.service.user.CredentialService;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @ModelAttribute
    public PasswordForm setupPasswordForm() {
        return new PasswordForm();
    }

    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    @RequestMapping(method = RequestMethod.GET)
    public String changeForm() {
        return "user/passwordChangeForm";
    }

    @TransactionTokenCheck
    @RequestMapping(method = RequestMethod.POST)
    public String change(
            @Validated PasswordForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return changeForm();
        }

        try {
            credentialService.change(
                    form.getUserId(), form.getCurrentPassword(), form.getPassword());
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return changeForm();
        } catch (ObjectOptimisticLockingFailureException e) {
            model.addAttribute(ResultMessages.error().add("e.sc.um.8005"));
            return changeForm();
        }

        return "forward:/authenticate";
    }

}
