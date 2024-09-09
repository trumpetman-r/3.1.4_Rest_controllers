package com.webcrudsecurityboot.service;

import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findUserById(id);
        return user.orElse(null);
    }

    @Override
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }
}