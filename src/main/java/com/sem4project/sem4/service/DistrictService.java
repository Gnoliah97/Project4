package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.dto.dtomodel.ProvinceDto;

import java.util.List;

public interface DistrictService {
    void createDistrict(DistrictDto districtDto);
    List<DistrictDto> getAllDistrict(Boolean isDisable);

    DistrictDto getDistrictById(Long id);

    DistrictDto updateDistrict(DistrictDto districtDto);

    void updateDisableDistrict(Long id, Boolean isDisable);

    void transferCommuneToProvince(DistrictDto districtDto, ProvinceDto provinceDto);
}
