package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.UserDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;
import com.sem4project.sem4.entity.User;

public class UserMapper {
    public static UserDto userDtoFromUser(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .build();
    }
    public static User userFromUserDto(UserDto userDto){
        return User.builder()
                .username(userDto.getUsername())
                .build();
    }
    public static User userFromLoginRequest(LoginRequest loginRequest){
        return User.builder()
                .username(loginRequest.getUsername())
                .password(loginRequest.getPassword())
                .build();
    }
    public static User userFromRegisterRequest(RegisterRequest registerRequest){
        return User.builder()
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .build();
    }
}
