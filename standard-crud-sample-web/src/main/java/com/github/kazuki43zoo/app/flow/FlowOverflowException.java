package com.github.kazuki43zoo.app.flow;

public class FlowOverflowException extends RuntimeException {

    @lombok.Getter
    private Flow flow;

    public FlowOverflowException(Flow flow) {
        super("Flow is overflow. id : " + flow.getId());
        this.flow = flow;
    }

}
