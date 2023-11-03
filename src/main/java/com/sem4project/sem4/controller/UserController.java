package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.dtomodel.UserInfoDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "User", description = "User API")
@RestController
@RequestMapping(value = "/api/v1/user")
@AllArgsConstructor
@CrossOrigin
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

    @RequestMapping(value = "/updateUserInfo/{id}", method = RequestMethod.PUT)
    @PostAuthorize("authentication.principal.id == id || hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> updateUserInfo(@PathVariable UUID id, @RequestBody @Valid UserInfoDto userInfoDto) {
        return ResponseEntity.ok()
                .body(
                        ResponseObject.builder()
                                .message("Update info success")
                                .data(userService.updateUserInfo(id, userInfoDto))
                                .build()
                );
    }
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getAllUser(@RequestParam(required = false) Boolean isDisable){
        return ResponseEntity.ok()
                .body(
                        ResponseObject.builder()
                                .message("Get all user success")
                                .data(userService.getAllUser(isDisable))
                                .build()
                );
    }
}
