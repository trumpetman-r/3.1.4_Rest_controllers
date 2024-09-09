package com.webcrudsecurityboot.config;

import com.webcrudsecurityboot.model.Role;
import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.service.RoleService;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class DatabaseInitializer {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DatabaseInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Transactional
    public void populateDatabase() {
        Role roleAdmin = new Role("ADMIN");
        roleService.saveRole(roleAdmin);

        Role roleUser = new Role("USER");
        roleService.saveRole(roleUser);

        User user1 = new User("Max", "Ivanov", "ivanov@mail.ru", 40, "root123", Set.of(roleAdmin));
        userService.saveUser(user1);

        User user2 = new User("Sergey", "Petrov", "petrov@mail.ru", 25, "root123", Set.of(roleUser));
        userService.saveUser(user2);

        User user3 = new User("Anastasiya", "Egorova", "egorova@mail.ru", 27, "root123", Set.of(roleUser, roleAdmin));
        userService.saveUser(user3);
    }
}