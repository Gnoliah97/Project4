package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BrandRepository extends BaseRepository<Brand, UUID> {
    Page<Brand> findAllByDisable(boolean isDisable, Pageable pageable);
    Long countByDisable(Boolean isDisable);
}
