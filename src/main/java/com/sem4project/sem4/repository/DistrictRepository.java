package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<Province, Long> {
}
