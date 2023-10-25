package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.Province;

public class ProvinceMapper {
    public static ProvinceDto provinceDtoFromProvince(Province province){
        return ProvinceDto.builder()
                .name(province.getName())
                .build();
    }
    public static Province provinceFromProvinceDto(ProvinceDto provinceDto){
        return Province.builder()
                .name(provinceDto.getName())
                .build();
    }
    public static void transferProvinceDtoToProvince(ProvinceDto provinceDto, Province province){
        province.setName(provinceDto.getName());
    }
}
