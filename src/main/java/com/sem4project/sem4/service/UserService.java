package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.UserDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;

public interface UserService {
    void login(LoginRequest loginRequest);
    void register(RegisterRequest request);
    void logout();
    UserDto getUserInfo();
}
