package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.RoleDto;
import com.sem4project.sem4.entity.Role;

public class RoleMapper {
    public static Role roleFromRoleDto(RoleDto roleDto){
        return Role.builder()
                .name(roleDto.getName())
                .build();
    }
    public static RoleDto roleDtoFromRole(Role role){
        return RoleDto.builder()
                .name(role.getName())
                .build();
    }
}
