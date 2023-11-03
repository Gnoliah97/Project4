package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.dto.dtomodel.ProvinceDto;

import java.util.List;
import java.util.UUID;

public interface DistrictService {
    void createDistrict(DistrictDto districtDto);
    List<DistrictDto> getAllDistrict(Boolean isDisable, int pageNumber, int pageSize, String sortBy);

    DistrictDto getDistrictById(UUID id);

    DistrictDto updateDistrict(UUID id, DistrictDto districtDto);

    void updateDisableDistrict(UUID id, Boolean isDisable);

    void transferCommuneToProvince(DistrictDto districtDto, ProvinceDto provinceDto);
    Long countDistrict(Boolean isDisable);
}
