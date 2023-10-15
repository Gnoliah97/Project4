package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.dtomodel.TokenDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.service.UserService;
import com.sem4project.sem4.util.JwtUtil;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
@PermitAll
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @RequestMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginRequest loginRequest) {
        try {
            userService.login(loginRequest);
            String token = jwtUtil.generateToken((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            TokenDto tokenDto = TokenDto.builder()
                    .jwtToken(token)
                    .build();
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(
                            ResponseObject.builder()
                                    .message("Login success")
                                    .data(tokenDto)
                                    .build()
                    );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(
                            ResponseObject.builder()
                                    .message(ex.getMessage())
                                    .build()
                    );
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> register(@RequestBody RegisterRequest registerRequest) {
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

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getInfo() {
        return ResponseEntity.ok()
                .body(
                        ResponseObject.builder()
                                .message("Get info success")
                                .data(userService.getUserInfo())
                                .build()
                );
    }
}
