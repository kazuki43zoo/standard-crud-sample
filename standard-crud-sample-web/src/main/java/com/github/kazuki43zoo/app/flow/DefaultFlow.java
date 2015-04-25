package com.github.kazuki43zoo.app.flow;

@lombok.ToString(callSuper = true)
@lombok.RequiredArgsConstructor
public class DefaultFlow extends AbstractFlow {

    @lombok.Getter
    private String finishPath;

    @lombok.Getter
    private String cancelPath;

    public static DefaultFlowBuilder builder() {
        return new DefaultFlowBuilder();
    }

    public static class DefaultFlowBuilder {
        private final DefaultFlow flow = new DefaultFlow();

        public DefaultFlowBuilder finishPath(String finishPath) {
            flow.finishPath = flow.appendControlParameters(finishPath);
            return this;
        }

        public DefaultFlowBuilder cancelPath(String cancelPath) {
            flow.finishPath = flow.appendControlParameters(cancelPath);
            return this;
        }

        public DefaultFlowBuilder callerFlowId(String callerFlowId) {
            flow.callerFlowId = callerFlowId;
            return this;
        }

        public DefaultFlow build() {
            if (flow.cancelPath == null) {
                flow.cancelPath = flow.finishPath;
            }
            return flow;
        }

    }

}
