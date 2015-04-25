package com.github.kazuki43zoo.app.flow;

import org.springframework.ui.Model;

import java.util.Map;

public interface Flow {

    String MODEL_NAME = "_flow";

    interface ParameterNames {
        String FLOW_ID = "_flowId";
        String FLOW_OPERATION = "_flowOperation";
    }

    enum Operation {
        BEGIN, TERMINATE
    }

    String getId();

    Map<String, String> asIdMap();

    String getCallerFlowId();

    void saveModel(Model model);

    void clearModel();

    Model getModel();

    boolean hasModel();

    boolean hasCallerFlow();

}
