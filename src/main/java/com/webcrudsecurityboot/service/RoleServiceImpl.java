package com.webcrudsecurityboot.service;

import com.webcrudsecurityboot.model.Role;
import com.webcrudsecurityboot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleRepository.findAllRoles();
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleRepository.findRoleById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.saveRole(role);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        roleRepository.updateRole(role);
    }

    @Override
    @Transactional
    public void deleteRoleById(Long id) {
        roleRepository.deleteRoleById(id);
    }
}