package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.exception.AuthException;
import com.sem4project.sem4.exception.CRUDException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerExceptionAdvice {
    @ExceptionHandler({AuthException.class})
    public ResponseEntity<ResponseObject> handleAuthException(Exception ex) {
        return ResponseEntity.status(401)
                .body(
                        ResponseObject.builder()
                                .message("Authentication failed")
                                .errors(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseObject> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(400)
                .body(
                        ResponseObject.builder()
                                .message("Not validated yet")
                                .errors(errors)
                                .build()
                );
    }

    @ExceptionHandler({CRUDException.class})
    public ResponseEntity<ResponseObject> handleCRUDException(Exception exception) {
        return ResponseEntity.status(400)
                .body(
                        ResponseObject.builder()
                                .message(exception.getMessage())
                                .data(false)
                                .build()
                );
    }

}
