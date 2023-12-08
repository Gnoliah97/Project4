package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNullFields;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends BaseRepository<Category, UUID> {
    @EntityGraph(attributePaths = {"categories"})
    Optional<Category> findById(UUID id);
    Long countByDisable(Boolean isDisable);
}
