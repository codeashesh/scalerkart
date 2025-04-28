package com.scalerkart.ecommerce.services.authentication.service;

import com.scalerkart.ecommerce.services.authentication.model.entity.Role;
import com.scalerkart.ecommerce.services.authentication.model.entity.RoleName;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    Optional<Role> findByName(RoleName name);

    boolean assignRole(Long id, String roleName);

    boolean revokeRole(Long id, String roleName);

    List<String> getUserRoles(Long id);
}
