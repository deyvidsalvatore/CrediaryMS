package com.deyvidsalvatore.crediary.mscustomers.application.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class StandardError {
    private LocalDateTime timestamp;
    private String error;
    private Integer status;
    private String path;

    public StandardError(LocalDateTime timestamp, String error, Integer status, String path) {
        this.timestamp = timestamp;
        this.error = error;
        this.status = status;
        this.path = path;
    }

}