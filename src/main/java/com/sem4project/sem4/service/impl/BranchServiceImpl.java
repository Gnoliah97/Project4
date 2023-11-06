package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.BranchDto;
import com.sem4project.sem4.dto.dtomodel.BrandDto;
import com.sem4project.sem4.entity.Branch;
import com.sem4project.sem4.entity.Brand;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.mapper.BranchMapper;
import com.sem4project.sem4.mapper.BrandMapper;
import com.sem4project.sem4.repository.BranchRepository;
import com.sem4project.sem4.service.BaseService;
import com.sem4project.sem4.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BranchServiceImpl implements BaseService<Branch, BranchDto> {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper = BranchMapper.INSTANCE;

    @Override
    public BranchDto getById(UUID id) {
        try {
            Branch brand = branchRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return branchMapper.toDto(brand);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Brand with id = " + id + " not found");
        }
    }

    @Override
    public List<BranchDto> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        return null;
    }

    @Override
    public List<BranchDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        return null;
    }

    @Override
    public BranchDto create(BranchDto brandDto) {
        return null;
    }

    @Override
    public BranchDto update(UUID id, BranchDto brandDto) {
        return null;
    }

    @Override
    public void updateDisable(UUID id) {

    }

    @Override
    public Long count(Boolean isDisable) {
        return null;
    }
}
