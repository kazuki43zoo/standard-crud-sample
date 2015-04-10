package com.github.kazuki43zoo.app.welcome;

import com.github.kazuki43zoo.domain.service.security.CustomUserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    @RequestMapping(value = "/", method = {RequestMethod.HEAD, RequestMethod.GET})
    public String home(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login?encourage";
        } else {
            return "welcome/home";
        }
    }

}
