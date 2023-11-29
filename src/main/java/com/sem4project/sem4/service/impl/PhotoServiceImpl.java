package com.sem4project.sem4.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sem4project.sem4.dto.dtomodel.PhotoDto;
import com.sem4project.sem4.entity.Photo;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.mapper.PhotoMapper;
import com.sem4project.sem4.repository.PhotoRepository;
import com.sem4project.sem4.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor

public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private final Cloudinary cloudinary;

    private final PhotoRepository photoRepository;
    private final PhotoMapper photoMapper = PhotoMapper.INSTANCE;

    @Override
    public PhotoDto upload(MultipartFile file) {
        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String name = result.get("public_id").toString();
            String url = result.get("url").toString();

            Photo dbPhoto = new Photo();
            dbPhoto.setName(name);
            dbPhoto.setUrl(url);
            photoRepository.save(dbPhoto);
        } catch (IOException e) {
            throw new UpdateResourceException(e);
        }
        return null;
    }

    @Override
    public List<PhotoDto> getAllPhoto() {
        List<Photo> photos = photoRepository.findAll();


        return null;
    }

    @Override
    public void deletePhoto(Long id) {

    }
}
