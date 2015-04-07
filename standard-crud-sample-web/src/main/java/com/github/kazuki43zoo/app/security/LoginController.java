package com.github.kazuki43zoo.app.security;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

@Controller
@RequestMapping("login")
@TransactionTokenCheck("login")
public class LoginController {

    @ModelAttribute
    public LoginForm setupLoginForm() {
        return new LoginForm();
    }

    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    @RequestMapping(method = {RequestMethod.HEAD, RequestMethod.GET})
    public String loginForm() {
        return "security/loginForm";
    }

    @TransactionTokenCheck
    @RequestMapping(method = RequestMethod.POST)
    public String login(@Validated LoginForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return loginForm();
        }

        return "forward:/authenticate";
    }

}
