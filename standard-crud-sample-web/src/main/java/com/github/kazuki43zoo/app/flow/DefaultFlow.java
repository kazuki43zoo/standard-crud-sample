package com.github.kazuki43zoo.app.flow;

@lombok.ToString(callSuper = true)
public class DefaultFlow extends AbstractFlow {

    @lombok.Getter
    private String finishPath;

    @lombok.Getter
    private String cancelPath;

    public static DefaultFlowBuilder builder() {
        return new DefaultFlowBuilder();
    }

    public static DefaultFlowBuilder builder(Flow callerFlow) {
        return new DefaultFlowBuilder().callerFlowId(callerFlow.getId());
    }

    public static class DefaultFlowBuilder {
        private final DefaultFlow flow = new DefaultFlow();

        public DefaultFlowBuilder finishPath(String finishPath) {
            flow.finishPath = finishPath;
            return this;
        }

        public DefaultFlowBuilder cancelPath(String cancelPath) {
            flow.cancelPath = cancelPath;
            return this;
        }

        public DefaultFlowBuilder callerFlowId(String callerFlowId) {
            flow.callerFlowId = callerFlowId;
            return this;
        }

        public DefaultFlow build() {
            flow.finishPath = flow.appendControlParameters(flow.finishPath);
            flow.cancelPath = flow.appendControlParameters(flow.cancelPath);
            if (flow.cancelPath == null) {
                flow.cancelPath = flow.finishPath;
            }
            return flow;
        }

    }

}
