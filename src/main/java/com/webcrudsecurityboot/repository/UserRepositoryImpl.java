package com.webcrudsecurityboot.repository;

import com.webcrudsecurityboot.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        return entityManager.createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}