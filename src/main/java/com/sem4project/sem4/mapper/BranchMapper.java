package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.BaseDto;
import com.sem4project.sem4.dto.dtomodel.BranchDto;
import com.sem4project.sem4.entity.BaseEntity;
import com.sem4project.sem4.entity.Branch;

public class BranchMapper implements BaseMapper<Branch, BranchDto>{
    @Override
    public BranchDto toDto(Branch branch) {
        return BranchDto.builder()
            .id(branch.getId())
            .name(branch.getName())
            .createdAt(branch.getCreatedAt())
            .updatedAt(branch.getUpdatedAt())
            .build();
    }

    @Override
    public Branch toEntity(BranchDto branchDto) {
        return Branch.builder()
                .name(branchDto.getName())
                .build();
    }

    @Override
    public void transferToDto(Branch branch, BranchDto branchDto) {

    }

    @Override
    public void transferToEntity(BranchDto branchDto, Branch branch) {

    }
}
