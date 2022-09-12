package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.validator.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class RegistryController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    public RegistryController(UserService userService, UserValidator userValidator, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String registration (@ModelAttribute("user") User user) {

        return "auth/registration";
    }

    @PostMapping("/registration")
    public String doRegistration (@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        String passwordCode = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordCode);

        userService.addUser(user);

        return "redirect:/login";
    }

}
