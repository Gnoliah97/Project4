package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.exception.CRUDException;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.ProvinceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProvinceServiceImpl implements ProvinceService {
    private final ModelMapper mapper;
    private final ProvinceRepository provinceRepository;

    @Override
    public void create(ProvinceDto provinceDto) {
        try {
            Province province = mapper.map(provinceDto, Province.class);
            provinceRepository.save(province);
        } catch (Exception e) {
            throw new CRUDException("can not create new province");
        }
    }

    @Override
    public List<ProvinceDto> getAllProvince() {
        try {
            List<Province> provinces = provinceRepository.findAll();
            return provinces.stream().map(province -> {
                ProvinceDto provinceDto = mapper.map(province, ProvinceDto.class);
                provinceDto.setDistricts(province.getDistricts().stream().map(district -> mapper.map(district, DistrictDto.class)).toList());
                return provinceDto;
            }).toList();
        } catch (Exception e) {
            throw new CRUDException("Get all fail !!");
        }
    }
}
