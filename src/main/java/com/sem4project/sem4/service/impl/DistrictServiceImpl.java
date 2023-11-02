package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.exception.CRUDException;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.mapper.DistrictMapper;
import com.sem4project.sem4.repository.DistrictRepository;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.DistrictService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictMapper districtMapper = DistrictMapper.INSTANCE;

    @Override
    public void createDistrict(DistrictDto districtDto) {
        try {
            Province province = provinceRepository.findById(districtDto.getProvince().getId()).orElse(new Province());
            District district = districtMapper.toEntity(districtDto);
            district.setProvince(province);
            districtRepository.save(district);
        } catch (Exception e) {
            throw new CRUDException("Create fail");
        }
    }

    @Override
    public List<DistrictDto> getAllDistrict(Boolean isDisable) {
        try {
            List<District> districts;
            if(isDisable == null){
                 districts = districtRepository.findAll();
            } else{
                districts = districtRepository.findAllByDisable(isDisable);
            }
            return districts.stream().map(districtMapper::toDto).toList();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Get all fail");
        }
    }

    @Override
    public DistrictDto getDistrictById(Long id) {
        try {
            District district = districtRepository.findById(id).orElseThrow(() -> new CRUDException("id" + id + "not found"));
            return districtMapper.toDto(district);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cant find District with id = " + id);
        }
    }

    @Override
    public DistrictDto updateDistrict(DistrictDto districtDto) {
        try {
            District district = districtRepository.findById(districtDto.getId()).orElseThrow(() -> new ResourceNotFoundException("District with id = " + districtDto.getId() + " not found"));
            districtMapper.transferToEntity(district, districtDto);
            districtRepository.save(district);
            return districtDto;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cant find District with id = " + districtDto.getId());
        }
    }

    @Override
    public void updateDisableDistrict(Long id, Boolean isDisable) {
        try {
            District district = districtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("District with id = " + id + " not found"));
            district.setDisable(isDisable);
            districtRepository.save(district);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cant find District with id = " + id);
        }
    }

    @Override
    public void transferCommuneToProvince(DistrictDto districtDto, ProvinceDto provinceDto) {
        District district = districtRepository.findById(districtDto.getId()).orElseThrow(() -> new CRUDException("District with id = " + districtDto.getId() + " not found"));
        Province province = provinceRepository.findById(provinceDto.getId()).orElseThrow(() -> new CRUDException("Province with id = " + provinceDto.getId() + " not found"));
        district.setProvince(province);
        districtRepository.save(district);
    }
}
