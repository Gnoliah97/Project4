package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.UserDto;
import com.sem4project.sem4.dto.dtomodel.UserInfoDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    void login(LoginRequest loginRequest);
    void register(RegisterRequest request);
    List<UserDto> getAllUser(Boolean isDisable);
    void logout();
    UserDto getUserInfo();
    UserInfoDto updateUserInfo(UUID id, UserInfoDto userInfoDto);
    Long countUser(Boolean isDisable);
}
