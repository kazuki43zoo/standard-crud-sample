package com.github.kazuki43zoo.app.flow;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Component
public class FlowHelper {

    @Inject
    FlowRepository flowRepository;

    public String redirectAndBeginFlow(String path, Flow newFlow, RedirectAttributes redirectAttributes) {
        flowRepository.save(newFlow);
        redirectAttributes.addAllAttributes(newFlow.asIdMap());
        return "redirect:" + path;
    }

    public String forwardAndBeginFlow(String path, Flow newFlow, Model model) {
        flowRepository.save(newFlow);
        return "forward:" + path + (path.contains("?") ? "&" : "?") + Flow.ParameterNames.FLOW_ID + "=" + newFlow.getId();
    }

    public void beginDefaultFlow(Model currentModel) {
        if (!currentModel.containsAttribute(Flow.MODEL_NAME)) {
            Flow callerFlow = DefaultFlow.builder().finishPath("/").build();
            flowRepository.save(callerFlow);
            currentModel.addAttribute(Flow.MODEL_NAME, callerFlow);
        }
    }

    public void terminateFlow(String flowId) {
        if (flowId == null) {
            return;
        }
        flowRepository.delete(flowId);
    }

    public void activateFlow(Flow flow, Model currentModel) {
        currentModel.addAttribute(Flow.MODEL_NAME, flow);
        if (flow.hasModel()) {
            currentModel.addAllAttributes(flow.getModel().asMap());
        }
    }

}
