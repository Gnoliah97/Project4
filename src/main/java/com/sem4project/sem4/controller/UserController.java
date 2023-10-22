package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.dtomodel.UserInfoDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.exception.AuthException;
import com.sem4project.sem4.service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
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

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.PUT)
    public ResponseEntity<ResponseObject> updateUserInfo(@RequestBody @Valid UserInfoDto userInfoDto) {
        return ResponseEntity.ok()
                .body(
                        ResponseObject.builder()
                                .message("Update info success")
                                .data(userService.updateUserInfo(userInfoDto))
                                .build()
                );
    }
}
