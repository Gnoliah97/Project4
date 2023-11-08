package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.mapper.DistrictMapper;
import com.sem4project.sem4.repository.DistrictRepository;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.DistrictService;
import com.sem4project.sem4.util.PageableUtil;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictMapper districtMapper = DistrictMapper.INSTANCE;

    @Override
    public void createDistrict(DistrictDto districtDto) {
        try {
            Province province = provinceRepository.findById(districtDto.getProvince().getId()).orElseThrow(IllegalArgumentException::new);
            District district = districtMapper.toEntity(districtDto);
            district.setProvince(province);
            districtRepository.save(district);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("District with id = " + districtDto.getId() + " not found");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Create district failed");
        }
    }

    @Override
    public List<DistrictDto> getAllDistrict(Boolean isDisable, int pageNumber, int pageSize, String sortBy) {
        try {
            long districtQuantity = this.countDistrict(isDisable);
            Pageable pageable = PageableUtil.calculatePageable(districtQuantity, pageNumber, pageSize, sortBy);

            List<District> districts;
            if(isDisable == null){
                 districts = districtRepository.findAll(pageable).stream().toList();
            } else{
                districts = districtRepository.findAllByDisable(isDisable, pageable).stream().toList();
            }
            return districts.stream().map(districtMapper::toDto).toList();
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Get districts failed");
        }
    }

    @Override
    public DistrictDto getDistrictById(UUID id) {
        try {
            District district = districtRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return districtMapper.toDto(district);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("District with id = " + id + " not found");
        }
    }

    @Override
    public DistrictDto updateDistrict(UUID id, DistrictDto districtDto) {
        try {
            District district = districtRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            districtMapper.transferToEntity(district, districtDto);
            districtRepository.save(district);
            return districtDto;
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("District with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Update district failed");
        }
    }

    @Override
    public void updateDisableDistrict(UUID id, Boolean isDisable) {
        try {
            District district = districtRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            district.setDisable(isDisable);
            districtRepository.save(district);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("District with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Create district failed");
        }
    }

    @Override
    public void transferCommuneToProvince(DistrictDto districtDto, ProvinceDto provinceDto) {
        District district = districtRepository.findById(districtDto.getId()).orElseThrow(() -> new UpdateResourceException("District with id = " + districtDto.getId() + " not found"));
        Province province = provinceRepository.findById(provinceDto.getId()).orElseThrow(() -> new UpdateResourceException("Province with id = " + provinceDto.getId() + " not found"));
        district.setProvince(province);
        districtRepository.save(district);
    }

    @Override
    public Long countDistrict(Boolean isDisable) {
        if(isDisable == null){
            return districtRepository.count();
        }
        return districtRepository.countByDisable(isDisable);
    }
}
