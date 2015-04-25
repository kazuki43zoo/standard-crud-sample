package com.github.kazuki43zoo.app.flow;

import org.springframework.ui.Model;

import java.util.Map;

public interface Flow {

    String MODEL_NAME = "_flow";

    interface ParameterNames {
        String FLOW_ID = "_flowId";
        String TERMINATE_TARGET_FLOW_ID = "_terminateTargetFlowId";
        String FLOW_OPERATION = "_flowOperation";
    }

    enum Operation {
        BEGIN, TERMINATE
    }

    String getId();

    Map<String, String> asIdMap();

    void saveModel(Model model);

    Model getModel();

    boolean hasModel();

    boolean hasCallerFlow();

}
