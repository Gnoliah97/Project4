package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.UserDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;
import com.sem4project.sem4.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mappings({})
    User fromRegisterRequest(RegisterRequest registerRequest);
    @Mappings({})
    User fromLoginRequest(LoginRequest loginRequest);
    @Mappings({
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "userInfo", ignore = true),
    })
    UserDto toDto(User user);

    @Mappings({
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "userInfo", ignore = true),
    })
    List<UserDto> toListDto(List<User> users);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "userInfo", ignore = true),
    })
    User toEntity(UserDto userDto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "userInfo", ignore = true),
    })
    List<User> toListEntity(List<UserDto> userDtos);
}
