package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;

import java.util.List;
import java.util.UUID;

public interface ProvinceService {
    ProvinceDto createProvince(ProvinceDto provinceDto);

    List<ProvinceDto> getAllProvince(Boolean isDisable, int pageNumber, int pageSize, String sortBy);

    ProvinceDto getProvinceById(UUID id);

    ProvinceDto updateProvince(UUID id, ProvinceDto provinceDto);
    void updateDisableProvince(UUID id, Boolean isDisable);
    Long countProvince(Boolean isDisable);
}
