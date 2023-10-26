package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.entity.District;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface DistrictMapper{
    DistrictMapper INSTANCE = Mappers.getMapper(DistrictMapper.class);
    DistrictDto toDto(District district);
    @Mappings({
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "branches", ignore = true),
    })
    List<DistrictDto> toListDto(List<District> districts);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "branches", ignore = true),
    })
    District toEntity(DistrictDto districtDto);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "branches", ignore = true),
    })
    List<District> toListEntity(List<DistrictDto> districtDtos);
    @Mappings({
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "branches", ignore = true),
    })
    void transferToDto(@MappingTarget DistrictDto districtDto, District district);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "branches", ignore = true),
    })
    void transferToEntity(@MappingTarget District district, DistrictDto districtDto);
}