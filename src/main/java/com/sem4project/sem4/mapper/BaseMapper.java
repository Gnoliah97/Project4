package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.BaseDto;
import com.sem4project.sem4.entity.BaseEntity;

public interface BaseMapper<T extends BaseEntity, U extends BaseDto> {
    U toDto(T t);
    T toEntity(U u);
    void transferToDto(T t, U u);
    void transferToEntity(U u, T t);
}
