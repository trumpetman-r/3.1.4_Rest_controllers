package com.webcrudsecurityboot.controller;

import com.webcrudsecurityboot.model.Role;
import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.service.RoleService;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public String getAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "index";
    }

    @GetMapping("/{id}")
    public String getUserById(@AuthenticationPrincipal User user, @PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(user.getId()));
        model.addAttribute("role", roleService.getRoleById(user.getId()));
        return "show";
    }

    @GetMapping("/add")
    public String addUserForm(Model model, @ModelAttribute("user") User user, @ModelAttribute("role") Role role) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("rolesSelected") Long[] rolesId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        Set<Role> roles = new HashSet<>();
        for (Long roleId : rolesId) {
            roles.add(roleService.getRoleById(roleId));
        }
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin/all";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("rolesSelected") Long[] rolesId) {
        Set<Role> roles = new HashSet<>();
        for (Long roleId : rolesId) {
            roles.add(roleService.getRoleById(roleId));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin/all";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/all";
    }
}