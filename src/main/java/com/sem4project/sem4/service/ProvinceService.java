package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;

import java.util.List;

public interface ProvinceService {
    ProvinceDto createProvince(ProvinceDto provinceDto);

    List<ProvinceDto> getAllProvince(Boolean isDisable);

    ProvinceDto getProvinceById(Long id);

    ProvinceDto updateProvince(ProvinceDto provinceDto);
    void updateDisableProvince(Long id, Boolean isDisable);
}
