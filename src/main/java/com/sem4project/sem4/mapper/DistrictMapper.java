package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.entity.District;

public class DistrictMapper {
    public static DistrictDto districtDtoFromDistrict(District district){
        return DistrictDto.builder()
                .name(district.getName())
                .build();
    }
    public static District districtFromDistrictDto(DistrictDto districtDto){
        return District.builder()
                .name(districtDto.getName())
                .build();
    }
    public static void transferDistrictDtoToDistrict(DistrictDto districtDto, District district){
        district.setName(district.getName());
    }
}
