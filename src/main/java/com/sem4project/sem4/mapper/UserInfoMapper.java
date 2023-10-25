package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.UserInfoDto;
import com.sem4project.sem4.entity.UserInfo;

public class UserInfoMapper implements BaseMapper<UserInfo, UserInfoDto>{
    @Override
    public UserInfoDto toDto(UserInfo userInfo) {
        return userInfo != null ? UserInfoDto.builder()
                .fullName(userInfo.getFullName())
                .address(userInfo.getAddress())
                .phone(userInfo.getPhone())
                .dob(userInfo.getDob())
                .build() : null;
    }

    @Override
    public UserInfo toEntity(UserInfoDto userInfoDto) {
        return UserInfo.builder()
                .fullName(userInfoDto.getFullName())
                .address(userInfoDto.getAddress())
                .phone(userInfoDto.getPhone())
                .dob(userInfoDto.getDob())
                .build();
    }

    @Override
    public void transferToDto(UserInfo userInfo, UserInfoDto userInfoDto) {
        userInfoDto.setAddress(userInfo.getAddress());
        userInfoDto.setDob(userInfo.getDob());
        userInfoDto.setPhone(userInfo.getPhone());
        userInfoDto.setFullName(userInfo.getFullName());
    }

    @Override
    public void transferToEntity(UserInfoDto userInfoDto, UserInfo userInfo) {
        userInfo.setAddress(userInfoDto.getAddress());
        userInfo.setDob(userInfoDto.getDob());
        userInfo.setPhone(userInfoDto.getPhone());
        userInfo.setFullName(userInfoDto.getFullName());
    }
}
