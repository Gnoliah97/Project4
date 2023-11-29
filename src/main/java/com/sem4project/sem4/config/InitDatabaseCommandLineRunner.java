package com.sem4project.sem4.config;

import com.sem4project.sem4.common.RoleEnum;
import com.sem4project.sem4.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitDatabaseCommandLineRunner implements CommandLineRunner {
    private RoleService roleService;
    @Override
    public void run(String... args) throws Exception {
//        if(roleService.count(null) < RoleEnum.values().length){
//            roleService.init();
//        }
    }
}
