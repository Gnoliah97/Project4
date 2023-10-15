package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionalRepository extends JpaRepository<Transactional, Long> {
}
