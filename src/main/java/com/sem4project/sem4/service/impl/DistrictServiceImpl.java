package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.exception.CRUDException;
import com.sem4project.sem4.repository.DistrictRepository;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.DistrictService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final ModelMapper mapper;
    private final ProvinceRepository provinceRepository;

    @Override
    public void create(DistrictDto districtDto) {
        try {
            Province province = provinceRepository.findById(districtDto.getProvince().getId()).orElse(new Province());
            District district = mapper.map(districtDto, District.class);
            district.setProvince(province);
            districtRepository.save(district);

        } catch (Exception e) {

            throw new CRUDException("Create fail");

        }
    }
}
