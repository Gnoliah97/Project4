package com.sem4project.sem4.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sem4project.sem4.dto.response.ResponseObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put(
                "timestamp",
                Instant.now());
        data.put(
                "exception",
                exception.getMessage());
        response.getOutputStream()
                .println(objectMapper.writeValueAsString(ResponseObject.builder()
                        .message("Unauthorized")
                        .data(data)
                        .build()
                ));
    }
}
