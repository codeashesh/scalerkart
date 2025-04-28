package com.scalerkart.ecommerce.services.authentication.service.impl;

import com.scalerkart.ecommerce.services.authentication.exception.wrapper.RoleNotFoundException;
import com.scalerkart.ecommerce.services.authentication.exception.wrapper.UserNotFoundException;
import com.scalerkart.ecommerce.services.authentication.model.entity.Role;
import com.scalerkart.ecommerce.services.authentication.model.entity.RoleName;
import com.scalerkart.ecommerce.services.authentication.model.entity.User;
import com.scalerkart.ecommerce.services.authentication.repository.RoleRepository;
import com.scalerkart.ecommerce.services.authentication.repository.UserRepository;
import com.scalerkart.ecommerce.services.authentication.service.UserRoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<Role> findByName(final RoleName name) {
        return Optional.ofNullable(roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role Not Found with name: " + name)));
    }

    @Transactional
    @Override
    public boolean assignRole(final Long id, final String roleName) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        final Role role = roleRepository.findByName(mapToRoleName(roleName))
                .orElseThrow(() -> new RoleNotFoundException("Role not found in system: " + roleName));
        if (user.getRoles().contains(role)) return false;
        user.getRoles().add(role);
        userRepository.save(user);
        return true;
    }

    @Transactional
    @Override
    public boolean revokeRole(final Long id, final String roleName) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        if (user.getRoles().removeIf(role -> role.name().equals(mapToRoleName(roleName)))) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getUserRoles(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        final List<String> roleNames = new ArrayList<>();
        user.getRoles().forEach(userRole -> roleNames.add(userRole.name().toString()));
        return roleNames;
    }

    private RoleName mapToRoleName(final String roleName) {
        return switch (roleName.toLowerCase()) {
            case "admin" -> RoleName.ADMIN;
            case "user" -> RoleName.USER;
            default -> null;
        };
    }

}
