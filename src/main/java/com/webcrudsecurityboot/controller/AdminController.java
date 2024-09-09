package com.webcrudsecurityboot.controller;

import com.webcrudsecurityboot.model.Role;
import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.service.RoleService;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/roles/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping("/roles")
    public void createRole(@RequestBody Role role) {
        roleService.saveRole(role);
    }

    @PutMapping("/roles/{id}")
    public void updateRole(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        roleService.updateRole(role);
    }

    @DeleteMapping("/roles/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }
}