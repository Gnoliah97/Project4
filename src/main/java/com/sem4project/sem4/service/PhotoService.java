package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.PhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    PhotoDto upload(MultipartFile file);
    List<PhotoDto> getAllPhoto();
    void deletePhoto(Long id);
}
