package com.webcrudsecurityboot.repository;

import com.webcrudsecurityboot.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    List<Role> findAllRoles();
    Optional<Role> findRoleById(Long id);
    void saveRole(Role role);
    void updateRole(Role updatedRole);
    void deleteRoleById(Long id);
}