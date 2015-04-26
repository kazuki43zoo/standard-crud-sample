package com.github.kazuki43zoo.app.flow;

import com.github.kazuki43zoo.core.message.Message;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@ControllerAdvice
public class FlowControllerAdvice {

    @Inject
    FlowRepository flowRepository;

    @Inject
    FlowManager flowManager;

    @ModelAttribute
    public void controlFlows(HttpServletRequest request, Model currentModel) {

        Flow.Operation flowOperation =
                EnumUtils.getEnum(
                        Flow.Operation.class,
                        request.getParameter(Flow.ParameterNames.FLOW_OPERATION));

        if (flowOperation == Flow.Operation.BEGIN) {
            flowManager.beginDefaultFlow(currentModel);
            return;
        }

        if (flowOperation == Flow.Operation.TERMINATE) {
            flowManager.terminateFlow(
                    request.getParameter(Flow.ParameterNames.TERMINATE_TARGET_FLOW_ID));
        }

        String flowId = request.getParameter(Flow.ParameterNames.FLOW_ID);
        if (flowId == null) {
            return;
        }

        Flow currentFlow = Optional.ofNullable(flowRepository.findOne(flowId))
                .orElseThrow(() -> new InvalidFlowException(flowId));
        flowManager.activateFlow(currentFlow, currentModel);

    }

    @ExceptionHandler
    public String handleInvalidFlowException(InvalidFlowException e, HttpServletRequest request) {
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        flashMap.putAll(new ExtendedModelMap()
                .addAttribute(Message.INVALID_FLOW.resultMessages()));
        return "redirect:/";
    }

}
