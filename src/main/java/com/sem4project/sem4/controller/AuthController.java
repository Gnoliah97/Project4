package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.dtomodel.TokenDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.exception.AuthException;
import com.sem4project.sem4.service.UserService;
import com.sem4project.sem4.util.JwtUtil;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth")
@PermitAll
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> login(@RequestBody @Valid LoginRequest loginRequest) {
        userService.login(loginRequest);
        String token = jwtUtil.generateToken((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        TokenDto tokenDto = TokenDto.builder()
                .jwtToken(token)
                .build();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization",
                "Bearer " + token);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(
                        ResponseObject.builder()
                                .message("Login success")
                                .data(tokenDto)
                                .build()
                );
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> register(@RequestBody @Valid RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.status(201)
                .body(
                        ResponseObject.builder()
                                .message("Register success")
                                .data(true)
                                .build()
                );
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> logout() {
        userService.logout();
        return ResponseEntity.ok()
                .body(
                        ResponseObject.builder()
                                .message("Logout success")
                                .data(true)
                                .build()
                );
    }
}
