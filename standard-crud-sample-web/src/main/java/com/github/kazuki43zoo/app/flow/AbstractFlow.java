package com.github.kazuki43zoo.app.flow;

import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@lombok.ToString(exclude = "model")
public abstract class AbstractFlow implements Flow {

    @lombok.Getter
    private final String id = UUID.randomUUID().toString();

    @lombok.Getter
    protected String callerFlowId;

    @lombok.Getter
    private Model model;

    public void saveModel(Model model) {
        this.model = model;
    }

    public final Map<String, String> asIdMap() {
        return Collections.singletonMap(ParameterNames.FLOW_ID, id);
    }

    public final boolean hasModel() {
        return model != null;
    }

    protected final String appendControlParameters(String path) {
        if (path == null) {
            return null;
        }
        StringBuilder pathBuilder = new StringBuilder(path);
        if (path.contains("?")) {
            pathBuilder.append("&");
        } else {
            pathBuilder.append("?");
        }
        pathBuilder.append(ParameterNames.TERMINATE_TARGET_FLOW_ID).append("=").append(id);
        pathBuilder.append("&").append(ParameterNames.FLOW_OPERATION).append("=").append(Operation.TERMINATE);
        if (callerFlowId != null) {
            pathBuilder.append("&").append(ParameterNames.FLOW_ID).append("=").append(callerFlowId);
        }
        return pathBuilder.toString();
    }

}
