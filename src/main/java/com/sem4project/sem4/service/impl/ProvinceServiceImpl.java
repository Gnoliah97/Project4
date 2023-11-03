package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.mapper.DistrictMapper;
import com.sem4project.sem4.mapper.ProvinceMapper;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.ProvinceService;
import com.sem4project.sem4.util.PageableUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not create new province");
        }
    }

    @Override
    public List<ProvinceDto> getAllProvince(Boolean isDisable, int pageNumber, int pageSize, String sortBy) {
        try {
            long provinceQuantity = this.countProvince(isDisable);
            Pageable pageable = PageableUtil.calculatePageable(provinceQuantity, pageNumber, pageSize, sortBy);

            List<Province> provinces;
            if (isDisable == null) {
                provinces = provinceRepository.findAll(pageable).stream().toList();
            } else {
                provinces = provinceRepository.findAllByDisable(isDisable, pageable).stream().toList();
            }
            return provinces.stream().map(province -> {
                ProvinceDto provinceDto = provinceMapper.toDto(province);
                provinceDto.setDistricts(districtMapper.toListDto(province.getDistricts()));
                return provinceDto;
            }).toList();
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Get provinces failed");
        }
    }

    @Override
    public ProvinceDto getProvinceById(UUID id) {
        try {
            Province province = provinceRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            ProvinceDto provinceDto = provinceMapper.toDto(province);
            provinceDto.setDistricts(districtMapper.toListDto(province.getDistricts()));
            return provinceDto;
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Province with id = " + id + " not found");
        }
    }

    @Override
    public ProvinceDto updateProvince(UUID id, ProvinceDto provinceDto) {
        try {
            Province province = provinceRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            provinceMapper.transferToEntity(province, provinceDto);
            Province updatedProvince = provinceRepository.save(province);
            provinceRepository.refresh(updatedProvince);
            return provinceMapper.toDto(updatedProvince);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            throw new ResourceNotFoundException("Province with id = " + id + " not found");
        }
    }

    @Override
    public void updateDisableProvince(UUID id, Boolean isDisable) {
        try {
            Province province = provinceRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            province.setDisable(isDisable);
            provinceRepository.save(province);
        } catch (IllegalArgumentException | EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Province with id = " + id + " not found");
        }
    }

    @Override
    public Long countProvince(Boolean isDisable) {
        if(isDisable == null){
            return provinceRepository.count();
        }
        return provinceRepository.countByDisable(isDisable);
    }
}
