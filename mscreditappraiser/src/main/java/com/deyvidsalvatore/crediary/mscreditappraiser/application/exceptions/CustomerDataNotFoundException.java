package com.deyvidsalvatore.crediary.mscreditappraiser.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerDataNotFoundException extends RuntimeException {
    public CustomerDataNotFoundException() {
        super("Customer Data was not found with the provided SSN!");
    }
}
