package com.sem4project.sem4.mapper;
import org.mapstruct.factory.Mappers;

public interface PhotoMapper {
    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);

}
