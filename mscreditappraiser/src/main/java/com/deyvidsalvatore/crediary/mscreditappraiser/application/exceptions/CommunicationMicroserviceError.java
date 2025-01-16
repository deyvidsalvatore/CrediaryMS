package com.deyvidsalvatore.crediary.mscreditappraiser.application.exceptions;

import lombok.Getter;

@Getter
public class CommunicationMicroserviceError extends RuntimeException {

    Integer status;

    public CommunicationMicroserviceError(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
