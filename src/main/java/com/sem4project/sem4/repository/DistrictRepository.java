package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends BaseRepository<District, Long> {
    List<District> findAllByDisable(boolean isDisable);
}
