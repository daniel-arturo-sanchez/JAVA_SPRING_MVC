package com.dsw.Project.controllers;

import com.dsw.Project.repositories.UserRepository;
import com.dsw.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserControllers {
    @Autowired
    private UserService us;

    @GetMapping("/usuarios")
    public String listaUsuarios(Model model) {
        model.addAttribute("users", us.list());
        return "users/index";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        us.delete(id);
        return "redirect:/usuarios";
    }
}
