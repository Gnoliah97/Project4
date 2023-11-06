package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.ImageDto;
import com.sem4project.sem4.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    Image uploadImage(MultipartFile file);
}
