package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.common.RoleEnum;
import com.sem4project.sem4.entity.Role;
import com.sem4project.sem4.repository.RoleRepository;
import com.sem4project.sem4.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void init() {
        Arrays.stream(RoleEnum.values()).forEach(r -> {
            roleRepository.save(Role.builder()
                    .name(r.name())
                    .build());
        });
    }

    @Override
    public Long countRole(Boolean isDisable) {
        if(isDisable == null){
            return roleRepository.count();
        }
        return roleRepository.countByDisable(isDisable);
    }
}
