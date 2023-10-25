package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.BrandDto;
import com.sem4project.sem4.entity.Brand;

public class BrandMapper implements BaseMapper<Brand, BrandDto>{
    @Override
    public BrandDto toDto(Brand brand) {
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }

    @Override
    public Brand toEntity(BrandDto brandDto) {
        return Brand.builder()
                .name(brandDto.getName())
                .build();
    }

    @Override
    public void transferToDto(Brand brand, BrandDto brandDto) {

    }

    @Override
    public void transferToEntity(BrandDto brandDto, Brand brand) {

    }
}
