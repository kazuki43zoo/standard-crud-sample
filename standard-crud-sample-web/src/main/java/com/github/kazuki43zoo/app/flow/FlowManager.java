package com.github.kazuki43zoo.app.flow;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Component
public class FlowManager {

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

    public void beginFlow(Flow newFlow, Model currentModel) {
        if (currentModel.containsAttribute(Flow.MODEL_NAME)) {
            Flow currentFlow = Flow.class.cast(currentModel.asMap().get(Flow.MODEL_NAME));
            flowRepository.delete(currentFlow.getId());
        }
        flowRepository.save(newFlow);
        currentModel.addAttribute(Flow.MODEL_NAME, newFlow);
    }

    public void beginDefaultFlow(Model currentModel) {
        if (!currentModel.containsAttribute(Flow.MODEL_NAME)) {
            Flow defaultFlow = DefaultFlow.builder().finishPath("/").build();
            flowRepository.save(defaultFlow);
            currentModel.addAttribute(Flow.MODEL_NAME, defaultFlow);
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
