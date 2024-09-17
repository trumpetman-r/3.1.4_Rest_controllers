package com.webcrudsecurityboot.config;

import com.webcrudsecurityboot.model.Role;
import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInitializer {

    private final UserService userService;
    private final Validator validator;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public DatabaseInitializer(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @Transactional
    public void populateDatabase() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        entityManager.persist(roleAdmin);
        entityManager.persist(roleUser);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleAdmin);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);

        Set<Role> adminUserRoles = new HashSet<>();
        adminUserRoles.add(roleAdmin);
        adminUserRoles.add(roleUser);

        User admin = new User("admin", "admin@example.com", "password", adminRoles);
        User user = new User("user", "user@example.com", "password", userRoles);
        User adminUser = new User("adminuser", "adminuser@example.com", "password", adminUserRoles);

        validateAndSaveUser(admin);
        validateAndSaveUser(user);
        validateAndSaveUser(adminUser);
    }

    private void validateAndSaveUser(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<User> violation : violations) {
                System.out.println(violation.getMessage());
            }
            throw new ConstraintViolationException(violations);
        }
        userService.saveUser(user);
    }
}