package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.UserInfoDto;
import com.sem4project.sem4.entity.UserInfo;

public class UserInfoMapper {
    public static UserInfoDto mapUserInfoToUserInfoDto(UserInfo userInfo){
        return userInfo != null ? UserInfoDto.builder()
                    .fullName(userInfo.getFullName())
                    .email(userInfo.getEmail())
                    .address(userInfo.getAddress())
                    .phone(userInfo.getPhone())
                    .dob(userInfo.getDob())
                .build() : null;
    }
    public static UserInfo mapUserInfoDtoToUserInfo(UserInfoDto userInfoDto){
        return UserInfo.builder()
                    .fullName(userInfoDto.getFullName())
                    .email(userInfoDto.getEmail())
                    .address(userInfoDto.getAddress())
                    .phone(userInfoDto.getPhone())
                    .dob(userInfoDto.getDob())
                .build();
    }
    public static void transferUserInfoToUserInfoDto(UserInfo userInfo, UserInfoDto userInfoDto){
        userInfoDto.setAddress(userInfo.getAddress());
        userInfoDto.setEmail(userInfo.getEmail());
        userInfoDto.setDob(userInfo.getDob());
        userInfoDto.setPhone(userInfo.getPhone());
        userInfoDto.setFullName(userInfo.getFullName());
    }
    public static void transferUserInfoDtoToUserInfo(UserInfoDto userInfoDto, UserInfo userInfo){
        userInfo.setAddress(userInfoDto.getAddress());
        userInfo.setEmail(userInfoDto.getEmail());
        userInfo.setDob(userInfoDto.getDob());
        userInfo.setPhone(userInfoDto.getPhone());
        userInfo.setFullName(userInfoDto.getFullName());
    }
}
