package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.RoleDto;
import com.sem4project.sem4.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper{

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    @Mappings({
            @Mapping(target = "users", ignore = true),
    })
    RoleDto toDto(Role role);
    @Mappings({
            @Mapping(target = "users", ignore = true),
    })
    List<RoleDto> toListDto(List<Role> roles);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "users", ignore = true),
    })
    Role toEntity(RoleDto roleDto);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "users", ignore = true),
    })
    List<Role> toListEntity(List<RoleDto> roleDtos);
}
