package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.BranchProductDto;
import com.sem4project.sem4.entity.BranchProduct;

public class BranchProductMapper implements BaseMapper<BranchProduct, BranchProductDto>{
    @Override
    public BranchProductDto toDto(BranchProduct branchProduct) {
        return BranchProductDto.builder()
                .id(branchProduct.getId())
                .quantity(branchProduct.getQuantity())
                .build();
    }

    @Override
    public BranchProduct toEntity(BranchProductDto branchProductDto) {
        return BranchProduct.builder()
                .quantity(branchProductDto.getQuantity())
                .build();
    }

    @Override
    public void transferToDto(BranchProduct branchProduct, BranchProductDto branchProductDto) {

    }

    @Override
    public void transferToEntity(BranchProductDto branchProductDto, BranchProduct branchProduct) {

    }
}
