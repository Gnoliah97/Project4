package com.sem4project.sem4.service.utils;

import com.sem4project.sem4.dto.dtomodel.BaseDto;
import com.sem4project.sem4.entity.BaseEntity;
import com.sem4project.sem4.repository.BaseRepository;
import com.sem4project.sem4.util.PageableUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class ServiceUtil {
    public static <T extends BaseEntity> List<T> getAllAvailable(
            BaseRepository<T, UUID> repository,
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String sortType
    ){
        List<T> entities;
        if(pageSize == null){
            Sort sort = PageableUtil.createSortFromString(sortBy, sortType);
            entities = repository.findAll(sort);
        } else{
            Long quantity = count(null, repository);
            Pageable pageable = PageableUtil.calculatePageable(quantity, pageNumber, pageSize, sortBy, sortType);
            entities = repository.findAll(pageable).stream().toList();
        }
        return entities;
    }

    public static <T extends BaseEntity> List<T> getAll(
            BaseRepository<T, UUID> repository,
            Boolean isDisable,
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String sortType
    ){
        List<T> entities;
        if(pageSize == null){
            Sort sort = PageableUtil.createSortFromString(sortBy, sortType);
            entities =
                    repository.findAllByDisable(isDisable, sort);
        } else{
            Long quantity = count(isDisable, repository);
            Pageable pageable = PageableUtil.calculatePageable(quantity, pageNumber, pageSize, sortBy, sortType);
            entities = repository.findAllByDisable(isDisable, pageable);
        }
        return entities;
    }

    public static  <T extends BaseEntity> Long count(Boolean isDisable, BaseRepository<T, UUID> repository) {
        if(isDisable == null){
            return repository.count();
        }
        return repository.countByDisable(isDisable);
    }
}
