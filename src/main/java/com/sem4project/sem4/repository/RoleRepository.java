package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {
    Role findByName(String name);
}
