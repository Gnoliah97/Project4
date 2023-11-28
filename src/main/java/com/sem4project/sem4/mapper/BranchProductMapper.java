package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.BranchProductDto;
import com.sem4project.sem4.entity.BranchProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface BranchProductMapper extends BaseMapper<BranchProduct, BranchProductDto>{
    BranchProductMapper INSTANCE = Mappers.getMapper(BranchProductMapper.class);
    @Mappings({
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "branch", ignore = true),
    })
    BranchProductDto toDto(BranchProduct branchProduct);

    @Mappings({
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "branch", ignore = true),
    })
    List<BranchProductDto> toListDto(List<BranchProduct> branchProducts);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "branch", ignore = true),
    })
    BranchProduct toEntity(BranchProductDto branchProductDto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "branch", ignore = true),
    })
    List<BranchProduct> toListEntity(List<BranchProductDto> branchProductDtos);

    @Mappings({
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "branch", ignore = true),
    })
    void transferToDto(@MappingTarget BranchProductDto branchProductDto, BranchProduct branchProduct);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "branch", ignore = true),
    })
    void transferToEntity(@MappingTarget BranchProduct branchProduct, BranchProductDto branchProductDto);
}
