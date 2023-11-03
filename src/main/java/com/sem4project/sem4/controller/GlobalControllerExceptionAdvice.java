package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.exception.AuthException;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.exception.UpdateResourceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerExceptionAdvice {
    @ExceptionHandler({AuthException.class})
    public ResponseEntity<ResponseObject> handleAuthException(AuthException ex) {
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
        return ResponseEntity.status(400)
                .body(
                        ResponseObject.builder()
                                .message("Not validated yet")
                                .errors(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler({UpdateResourceException.class})
    public ResponseEntity<ResponseObject> handleUpdateResourceException(UpdateResourceException ex) {
        return ResponseEntity.status(400)
                .body(
                        ResponseObject.builder()
                                .message("Update resource failed")
                                .errors(ex.getMessage())
                                .build()
                );
    }
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ResponseObject> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(400)
                .body(
                        ResponseObject.builder()
                                .message("Get resource failed")
                                .errors(ex.getMessage())
                                .build()
                );
    }

}
