package com.dsw.Project.controllers;

import com.dsw.Project.models.User;
import com.dsw.Project.services.RoleService;
import com.dsw.Project.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    UserService usersService;

    @Autowired
    RoleService rolesService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user",new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        String result;
        if(bindingResult.hasErrors()){
            return "auth/register";
        } else {
            try{
                usersService.create(user);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            result = "redirect:/animales";
        }
        return result;
    }
}
