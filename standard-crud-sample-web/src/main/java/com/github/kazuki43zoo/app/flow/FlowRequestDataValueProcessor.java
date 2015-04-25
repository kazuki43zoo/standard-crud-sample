package com.github.kazuki43zoo.app.flow;

import org.springframework.stereotype.Component;
import org.terasoluna.gfw.web.mvc.support.RequestDataValueProcessorAdaptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class FlowRequestDataValueProcessor extends RequestDataValueProcessorAdaptor {

    @Override
    public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
        Object flow = request.getAttribute(Flow.MODEL_NAME);
        if (flow == null) {
            return null;
        }
        return Flow.class.cast(flow).asIdMap();
    }

}
