package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;

import java.util.List;

public interface ProvinceService {
    void create(ProvinceDto provinceDto);
    List<ProvinceDto> getAllProvince();
}
