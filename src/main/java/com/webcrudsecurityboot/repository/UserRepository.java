package com.webcrudsecurityboot.repository;

import com.webcrudsecurityboot.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    void saveUser(User user);
    void updateUser(User updatedUser);
    void deleteUserById(Long id);
    User findUserByEmail(String email);
}