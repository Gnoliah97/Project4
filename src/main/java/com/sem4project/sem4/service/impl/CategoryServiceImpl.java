package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.CategoryDto;
import com.sem4project.sem4.entity.Branch;
import com.sem4project.sem4.entity.Brand;
import com.sem4project.sem4.entity.Category;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.mapper.CategoryMapper;
import com.sem4project.sem4.repository.CategoryRepository;
import com.sem4project.sem4.service.CategoryService;
import com.sem4project.sem4.service.utils.ServiceUtil;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
    @Override
    public CategoryDto getById(UUID id) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return categoryMapper.toDto(category);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Category with id = " + id + " not found");
        }
    }

    @Override
    public List<CategoryDto> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            if(isDisable != null && isDisable){
                return getAllAvailable(pageNumber, pageSize, sortBy, sortType);
            }
            List<Category> categories = ServiceUtil.getAll(categoryRepository, isDisable, pageNumber, pageSize, sortBy, sortType);

            return categories.stream().map(categoryMapper::toDto).toList();
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get categories failed");
        }
    }

    @Override
    public List<CategoryDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            List<Category> categories = ServiceUtil.getAllAvailable(categoryRepository, pageNumber, pageSize, sortBy, sortType);
            return categoryMapper.toListDto(categories);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get districts failed");
        }
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        try {
            Category category = categoryMapper.toEntity(categoryDto);
            Category createdCategory = categoryRepository.save(category);
//            categoryRepository.refresh(createdCategory);
            return categoryMapper.toDto(createdCategory);
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not create new category");
        }
    }

    @Override
    public CategoryDto update(UUID id, CategoryDto categoryDto) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            categoryMapper.transferToEntity(category, categoryDto);
            Category updatedCategory = categoryRepository.save(category);
//            categoryRepository.refresh(updatedCategory);
            return categoryMapper.toDto(updatedCategory);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("District with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Update category failed");
        }
    }

    @Override
    public void updateDisable(UUID id) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            category.setDisable(!category.isDisable());
            categoryRepository.save(category);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Category with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Create category failed");
        }
    }
}
