package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.exception.CRUDException;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.mapper.DistrictMapper;
import com.sem4project.sem4.mapper.ProvinceMapper;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.ProvinceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper = ProvinceMapper.INSTANCE;
    private final DistrictMapper districtMapper = DistrictMapper.INSTANCE;

    @Override
    public ProvinceDto createProvince(ProvinceDto provinceDto) {
        try {
            Province province = provinceMapper.toEntity(provinceDto);
            Province createdProvince = provinceRepository.save(province);
            provinceRepository.refresh(createdProvince);
            return provinceMapper.toDto(createdProvince);
        } catch (Exception e) {
            throw new CRUDException("Can not create new province");
        }
    }

    @Override
    public List<ProvinceDto> getAllProvince(Boolean isDisable) {
        try {
            List<Province> provinces;
            if (isDisable == null) {
                provinces = provinceRepository.findAll();
            } else {
                provinces = provinceRepository.findAllByDisable(isDisable);
            }
            return provinces.stream().map(province -> {
                ProvinceDto provinceDto = provinceMapper.toDto(province);
                provinceDto.setDistricts(districtMapper.toListDto(province.getDistricts()));
                return provinceDto;
            }).toList();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Get all fail!!");
        }
    }

    @Override
    public ProvinceDto getProvinceById(Long id) {
        try {
            Province province = provinceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cant find Province with id = " + id));
            ProvinceDto provinceDto = provinceMapper.toDto(province);
            provinceDto.setDistricts(districtMapper.toListDto(province.getDistricts()));
            return provinceDto;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cant find Province with id = " + id);
        }
    }

    @Override
    public ProvinceDto updateProvince(ProvinceDto provinceDto) {
        try {
            Province province = provinceRepository.findById(provinceDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Cant find Province with id = " + provinceDto.getId()));
            provinceMapper.transferToEntity(province, provinceDto);
            Province updatedProvince = provinceRepository.save(province);
            provinceRepository.refresh(updatedProvince);
            return provinceMapper.toDto(updatedProvince);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cant find Province with id = " + provinceDto.getId());
        }
    }

    @Override
    public void updateDisableProvince(Long id, Boolean isDisable) {
        try {
            Province province = provinceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cant find Province with id =" + id));
            province.setDisable(isDisable);
            provinceRepository.save(province);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Cant find Province with id =" + id);
        }
    }
}
