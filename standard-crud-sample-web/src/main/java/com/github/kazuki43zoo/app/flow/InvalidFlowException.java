package com.github.kazuki43zoo.app.flow;

public class InvalidFlowException extends RuntimeException {

    @lombok.Getter
    private String id;

    public InvalidFlowException(String id) {
        super("Invalid flow detected. id : " + id);
        this.id = id;
    }

}
