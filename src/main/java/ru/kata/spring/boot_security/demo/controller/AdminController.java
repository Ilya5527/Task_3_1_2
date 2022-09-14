package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/showUsers")
    public String usersList(ModelMap modelMap) {

        List<User> userList = userService.getAllUsers();
        modelMap.addAttribute("users", userList);

        return "userList";
    }

    @GetMapping("/addUser")
    public String addUser(ModelMap model) {
        List<Role> role = roleService.allRoles();
        model.addAttribute("roles", role);
        model.addAttribute("user", new User());
        return "addUser";
    }


    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
                return "addUser";
        }

        userService.addUser(user);
        return "redirect:/admin/showUsers";
    }

    @DeleteMapping("/{id}")
    public String getUser(@PathVariable("id") long id) {

        userService.deleteUser(id);

        return "redirect:/admin/showUsers";
    }

    @GetMapping("/{id}/updateUser")
    public String update(@PathVariable("id") long id, ModelMap model) {
        User user = userService.getUserById(id);
        List<Role> roles = roleService.allRoles();

        model.addAttribute("user", user);
        model.addAttribute("roleList", roles);
        return "updateUser";
    }


    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "updateUser";
        }

        userService.updateUser(user);
        return "redirect:/admin/showUsers";
    }

    @PutMapping("/{id}")
    public String giveRoleAdmin(@PathVariable("id") long id) {
        Role role = roleService.getAdminRole();
        User user = userService.getUserById(id);
        user.setRoles(role);

        userService.updateUser(user);

        return "redirect:/admin/showUsers";
    }

    @PostMapping("/{id}")
    public String giveRoleUser(@PathVariable("id") long id) {
        Role role = roleService.getDefaultRole();
        User user = userService.getUserById(id);

        user.setRoles(role);

        userService.updateUser(user);

        return "redirect:/admin/showUsers";
    }
}
