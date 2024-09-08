package com.webcrudsecurityboot.repository;

import com.webcrudsecurityboot.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    List<Role> getAllRoles();
    Role show(Long id);
    void save(Role role);
    void update(Role updatedRole);
    void delete(Long id);
    List<Role> findAll();
    Optional<Role> findById(Long id);
}