package com.github.kazuki43zoo.app.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    @RequestMapping(value = "/", method = {RequestMethod.HEAD, RequestMethod.GET})
    public String home() {
        return "welcome/home";
    }

}
