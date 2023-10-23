package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.dto.dtomodel.ProductDto;
import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.District;

import java.util.List;

public interface DistrictService {
    void create(DistrictDto districtDto);
    List<DistrictDto> getAllDistrict();

    DistrictDto getDistrict(Long id);

    DistrictDto updateDistrict(DistrictDto districtDto);

    void deleteDistrict(DistrictDto districtDto);

    void transferCommuneToProvince(DistrictDto districtDto, ProvinceDto provinceDto);
}
