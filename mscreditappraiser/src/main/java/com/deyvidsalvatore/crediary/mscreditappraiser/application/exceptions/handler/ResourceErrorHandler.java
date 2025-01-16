package com.deyvidsalvatore.crediary.mscreditappraiser.application.exceptions.handler;

import com.deyvidsalvatore.crediary.mscreditappraiser.application.exceptions.CommunicationMicroserviceError;
import com.deyvidsalvatore.crediary.mscreditappraiser.application.exceptions.CustomerDataNotFoundException;
import com.deyvidsalvatore.crediary.mscreditappraiser.application.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResourceErrorHandler {

    @ExceptionHandler(CustomerDataNotFoundException.class)
    public ResponseEntity<StandardError> handleCustomerDataNotFoundException(
            CustomerDataNotFoundException ex,
            HttpServletRequest request) {
        var standardError = new StandardError(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(CommunicationMicroserviceError.class)
    public ResponseEntity<StandardError> handleCommunicationMicroserviceError(CommunicationMicroserviceError ex, HttpServletRequest request) {
        var standardError = new StandardError(LocalDateTime.now(), ex.getMessage(), HttpStatus.BAD_GATEWAY.value(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(standardError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleExceptionServer(Exception ex, HttpServletRequest request) {
        var standardError = new StandardError(LocalDateTime.now(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }

}