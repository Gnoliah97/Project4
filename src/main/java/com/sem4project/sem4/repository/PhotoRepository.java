package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends BaseRepository<Photo, Long> {
}
