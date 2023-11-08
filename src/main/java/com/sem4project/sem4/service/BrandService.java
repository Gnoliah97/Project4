package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.BrandDto;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    BrandDto getBrandById(UUID id);
    List<BrandDto> getAllBrandDto(Boolean isDisable, int pageNumber, int pageSize, String sortBy);
    BrandDto createBrand(BrandDto brandDto);
    BrandDto updateBrand(BrandDto brandDto);
    Long countBrand(Boolean isDisable);
}
