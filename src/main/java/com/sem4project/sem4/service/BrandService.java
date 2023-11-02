package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.BrandDto;

import java.util.List;

public interface BrandService {
    BrandDto getBrandById(Long id);
    List<BrandDto> getAllBrandDto(Boolean isDisable);
    BrandDto createBrand(BrandDto brandDto);
    BrandDto updateBrand(BrandDto brandDto);
}
