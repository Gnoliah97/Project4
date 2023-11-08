package com.sem4project.sem4.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sem4project.sem4.dto.dtomodel.PhotoDto;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.repository.PhotoRepository;
import com.sem4project.sem4.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service

public class PhotoServiceImpl implements PhotoService {
    private Cloudinary cloudinary;
    private PhotoRepository photoRepository;
    @Override
    public PhotoDto upload(MultipartFile file) {
        try {
           Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        } catch (IOException e) {
            throw new UpdateResourceException(e);
        }
        return null;
    }
    @Override
    public List<PhotoDto> getAllPhoto() {
        return null;
    }

    @Override
    public void deletePhoto(Long id) {

    }
}
