package com.github.kazuki43zoo.app.flow;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class FlowControllerAdvice {

    @Inject
    FlowRepository flowRepository;

    @Inject
    FlowHelper flowHelper;

    @ModelAttribute
    public void controlFlows(HttpServletRequest request, Model currentModel) {

        Flow.Operation flowOperation =
                EnumUtils.getEnum(
                        Flow.Operation.class,
                        request.getParameter(Flow.ParameterNames.FLOW_OPERATION));

        if (flowOperation == Flow.Operation.BEGIN) {
            flowHelper.beginDefaultFlow(currentModel);
            return;
        }

        if (flowOperation == Flow.Operation.TERMINATE) {
            flowHelper.terminateFlow(
                    request.getParameter(Flow.ParameterNames.TERMINATE_TARGET_FLOW_ID));
        }

        String flowId = request.getParameter(Flow.ParameterNames.FLOW_ID);
        if (flowId == null) {
            return;
        }

        Flow currentFlow = flowRepository.findOne(flowId);
        if (currentFlow == null) {
            throw new InvalidFlowException(flowId);
        }
        flowHelper.activateFlow(currentFlow, currentModel);

    }

}
