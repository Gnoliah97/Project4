package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.BrandDto;
import com.sem4project.sem4.entity.Brand;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.mapper.BrandMapper;
import com.sem4project.sem4.repository.BrandRepository;
import com.sem4project.sem4.service.BrandService;
import com.sem4project.sem4.util.PageableUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper = BrandMapper.INSTANCE;
    @Override
    public BrandDto getBrandById(UUID id) {
        try{
            Brand brand = brandRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return brandMapper.toDto(brand);
        } catch(IllegalArgumentException ex){
            throw new ResourceNotFoundException("Brand with id = " + id + " not found");
        }
    }

    @Override
    public List<BrandDto> getAllBrandDto(Boolean isDisable, int pageNumber, int pageSize, String sortBy) {
        try{
            long brandQuantity = this.countBrand(isDisable);
            Pageable pageable = PageableUtil.calculatePageable(brandQuantity, pageNumber, pageSize, sortBy);
            List<Brand> brands;
            if(isDisable == null){
                brands = brandRepository.findAll(pageable).stream().toList();
            } else {
                brands = brandRepository.findAllByDisable(isDisable, pageable).stream().toList();
            }
            return brandMapper.toListDto(brands);
        }catch (IllegalArgumentException ex){
            throw new ResourceNotFoundException("Get brands failed");
        }
    }

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        return null;
    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto) {
        return null;
    }

    @Override
    public Long countBrand(Boolean isDisable) {
        if(isDisable == null){
            return brandRepository.count();
        }
        return brandRepository.countByDisable(isDisable);
    }
}
