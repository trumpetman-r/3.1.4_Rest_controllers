package com.webcrudsecurityboot.config;

import com.webcrudsecurityboot.model.Role;
import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInitializer {

    private final UserService userService;

    @Autowired
    public DatabaseInitializer(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public void populateDatabase() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        userService.saveRole(roleAdmin);
        userService.saveRole(roleUser);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleAdmin);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);

        Set<Role> adminUserRoles = new HashSet<>();
        adminUserRoles.add(roleAdmin);
        adminUserRoles.add(roleUser);

        User user1 = new User("Max", "Ivanov", "ivanov@mail.ru", 40, "root123", adminRoles);
        User user2 = new User("Sergey", "Petrov", "petrov@mail.ru", 25, "root123", userRoles);
        User user3 = new User("Anastasiya", "Egorova", "egorova@mail.ru", 27, "root123", adminUserRoles);

        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
    }
}