package com.github.kazuki43zoo.app;

import org.terasoluna.gfw.web.mvc.support.RequestDataValueProcessorAdaptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ModelAttributeRequestDataValueProcessor extends RequestDataValueProcessorAdaptor {

    private final List<String> targetAttributeNames;

    public ModelAttributeRequestDataValueProcessor(String... targetAttributeNames) {
        this.targetAttributeNames = Arrays.asList(targetAttributeNames);
    }

    @Override
    public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
        Map<String, String> hiddenFields = new LinkedHashMap<>();
        for (String targetAttributeName : targetAttributeNames) {
            Object value = request.getAttribute(targetAttributeName);
            if (value != null) {
                hiddenFields.put(targetAttributeName, value.toString());
            }
        }
        return hiddenFields;
    }

}
