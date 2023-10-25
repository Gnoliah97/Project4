package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.Province;

import java.util.List;

public interface ProvinceService {
    void create(ProvinceDto provinceDto);
    List<ProvinceDto> getAllProvince();

    ProvinceDto getProvince(Long id);

    ProvinceDto updateProvince(ProvinceDto provinceDto);

    void setDisableProvince(ProvinceDto provinceDto, boolean isDisable);

    List<ProvinceDto> getAllAvailableProvince();
}
