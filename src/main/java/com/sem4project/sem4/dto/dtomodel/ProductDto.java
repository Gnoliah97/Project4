package com.sem4project.sem4.dto.dtomodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto extends BaseDto{
    private String title;
    private String productCode;
    private String warrantyPeriod;
    private Double cost;
    private Double promotional;
    private boolean status;
    private List<ImageDto> photos;
    private String video;
    private List<GiftDto> gifts;
    private List<SpecificationDto> specifications;
    private PostDto post;
    private List<CategoryDto> categories;
    private BrandDto brand;
    private List<RateDto> rates;
    private List<BranchProductDto> branchProductDtos;
}
