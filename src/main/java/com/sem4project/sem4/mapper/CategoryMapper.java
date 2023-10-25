package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.CategoryDto;
import com.sem4project.sem4.entity.Category;

public class CategoryMapper implements BaseMapper<Category, CategoryDto>{
    @Override
    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        return Category.builder()
                .name(categoryDto.getName())
                .build();
    }

    @Override
    public void transferToDto(Category category, CategoryDto categoryDto) {

    }

    @Override
    public void transferToEntity(CategoryDto categoryDto, Category category) {

    }
}
