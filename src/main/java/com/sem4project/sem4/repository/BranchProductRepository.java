package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.BranchProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchProductRepository extends BaseRepository<BranchProduct, Long> {
}
