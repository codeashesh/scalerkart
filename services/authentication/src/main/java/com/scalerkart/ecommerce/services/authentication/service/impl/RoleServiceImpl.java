package com.scalerkart.ecommerce.services.authentication.service.impl;

import com.scalerkart.ecommerce.services.authentication.model.entity.Role;
import com.scalerkart.ecommerce.services.authentication.model.entity.RoleName;
import com.scalerkart.ecommerce.services.authentication.repository.RoleRepository;
import com.scalerkart.ecommerce.services.authentication.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired 
    RoleRepository roleRepository;

    @Transactional
    @Override
    public void initializeRole() {
        Role admin = new Role();
        admin.name(RoleName.ADMIN);
        roleRepository.save(admin);
        Role user = new Role();
        user.name(RoleName.USER);
        roleRepository.save(user);
    }
}
