package com.deyvidsalvatore.crediary.mscustomers.application.exceptions.handler;

import com.deyvidsalvatore.crediary.mscustomers.application.exceptions.CustomerDataIntegrationViolation;
import com.deyvidsalvatore.crediary.mscustomers.application.exceptions.CustomerNotFound;
import com.deyvidsalvatore.crediary.mscustomers.application.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class ResourceErrorHandler {
    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<StandardError> handleCustomerNotFoundException(
            CustomerNotFound ex,
            HttpServletRequest request) {
        var standardError = new StandardError(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Map<String, String>> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> Map.of("field", fieldError.getField(), "message", Objects.requireNonNull(fieldError.getDefaultMessage())))
                .toList();

        Map<String, Object> errorResponse = Map.of(
                "field", "Fail in field validations",
                "fieldErrors", fieldErrors
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CustomerDataIntegrationViolation.class)
    public ResponseEntity<StandardError> handleCustomerDataIntegration(CustomerDataIntegrationViolation ex, HttpServletRequest request) {
        var standardError = new StandardError(LocalDateTime.now(), ex.getMessage(), HttpStatus.CONFLICT.value(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(standardError);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleExceptionServer(Exception ex, HttpServletRequest request) {
        var standardError = new StandardError(LocalDateTime.now(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }

}