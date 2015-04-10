package com.github.kazuki43zoo.app;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice
public class AppGlobalModelAttributeBinder {

    @ModelAttribute("backwardQueryString")
    public String bindBackwardQueryString(
            @RequestParam(value = "backwardQueryString", required = false) String backwardQueryString) {
        return backwardQueryString;
    }

}
