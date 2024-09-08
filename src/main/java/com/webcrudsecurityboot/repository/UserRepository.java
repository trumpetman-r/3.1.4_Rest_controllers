package com.webcrudsecurityboot.repository;

import com.webcrudsecurityboot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUsers();
    User show(Long id);
    void save(User user);
    void update(User updatedUser);
    void delete(Long id);
    List<User> findAll();
    Optional<User> findById(Long id);
    void deleteById(Long id);
    User findByName(String email);
}