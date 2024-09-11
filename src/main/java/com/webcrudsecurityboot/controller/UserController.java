package com.webcrudsecurityboot.controller;

import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userService.getUserByEmail(currentUsername);
        return List.of(currentUser);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userService.getUserByEmail(currentUsername);

        if (!currentUser.getId().equals(id)) {
            throw new SecurityException("You are not authorized to view this user");
        }

        return currentUser;
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userService.getUserByEmail(currentUsername);

        if (!currentUser.getEmail().equals(user.getEmail())) {
            throw new SecurityException("You are not authorized to create this user");
        }

        userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userService.getUserByEmail(currentUsername);

        if (!currentUser.getId().equals(id)) {
            throw new SecurityException("You are not authorized to update this user");
        }

        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userService.getUserByEmail(currentUsername);

        if (!currentUser.getId().equals(id)) {
            throw new SecurityException("You are not authorized to delete this user");
        }

        userService.deleteUserById(id);
    }
}