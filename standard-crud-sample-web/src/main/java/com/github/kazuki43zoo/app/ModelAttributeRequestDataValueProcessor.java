package com.github.kazuki43zoo.app;

import org.terasoluna.gfw.web.mvc.support.RequestDataValueProcessorAdaptor;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class ModelAttributeRequestDataValueProcessor extends RequestDataValueProcessorAdaptor {

    private final List<String> targetAttributeNames;

    public ModelAttributeRequestDataValueProcessor(String... targetAttributeNames) {
        this.targetAttributeNames = Arrays.asList(targetAttributeNames);
    }

    @Override
    public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
        Map<String, String> hiddenFields = new LinkedHashMap<>();
        targetAttributeNames.forEach(targetAttributeName -> {
            Optional.ofNullable(request.getAttribute(targetAttributeName))
                    .ifPresent(value -> {
                        hiddenFields.put(targetAttributeName, value.toString());
                    });
        });
        return hiddenFields;
    }

}
