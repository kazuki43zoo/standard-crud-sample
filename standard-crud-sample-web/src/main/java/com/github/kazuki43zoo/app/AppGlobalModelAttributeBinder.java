package com.github.kazuki43zoo.app;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice
public class AppGlobalModelAttributeBinder {

    @ModelAttribute(ModelAttributeNames.BACKWARD_QUERY_STRING)
    public String bindBackwardQueryString(
            @RequestParam(value = ModelAttributeNames.BACKWARD_QUERY_STRING, required = false) String backwardQueryString) {
        return backwardQueryString;
    }

}
