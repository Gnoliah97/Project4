package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.RoleDto;
import com.sem4project.sem4.entity.Role;

public class RoleMapper implements BaseMapper<Role, RoleDto>{
    @Override
    public RoleDto toDto(Role role) {
        return RoleDto.builder()
                .name(role.getName())
                .build();
    }

    @Override
    public Role toEntity(RoleDto roleDto) {
        return Role.builder()
                .name(roleDto.getName())
                .build();
    }

    @Override
    public void transferToDto(Role role, RoleDto roleDto) {

    }

    @Override
    public void transferToEntity(RoleDto roleDto, Role role) {

    }
}
