package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.UserDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;
import com.sem4project.sem4.entity.User;
public class UserMapper implements BaseMapper<User, UserDto>{
    public User fromLoginRequest(LoginRequest loginRequest){
        return User.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();
    }
    public User fromRegisterRequest(RegisterRequest registerRequest){
        return User.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();
    }
    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .build();
    }

    @Override
    public User toEntity(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .build();
    }

    @Override
    public void transferToDto(User user, UserDto userDto) {

    }

    @Override
    public void transferToEntity(UserDto userDto, User user) {

    }
}
