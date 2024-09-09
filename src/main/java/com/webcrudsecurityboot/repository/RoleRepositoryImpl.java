package com.webcrudsecurityboot.repository;

import com.webcrudsecurityboot.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> findAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Optional<Role> findRoleById(Long id) {
        Role role = entityManager.find(Role.class, id);
        return Optional.ofNullable(role);
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(Role updatedRole) {
        entityManager.merge(updatedRole);
    }

    @Override
    public void deleteRoleById(Long id) {
        Role role = entityManager.find(Role.class, id);
        if (role != null) {
            entityManager.remove(role);
        }
    }
}