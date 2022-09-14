package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping()
    public String showUserInfo(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", "Hello " + auth.getName() + ", welcome to my website");
        return "user";
    }

}
