package com.dsw.Project.controllers;

import com.dsw.Project.repositories.UserRepository;
import com.dsw.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@PreAuthorize("hasRole('ADMIN')")
@Controller
public class UserControllers {
    @Autowired
    private UserService us;

    @GetMapping("/usuarios")
    public String listaUsuarios(Model model) {
        UserDetails user = us.loadUserByUsername(getAuthenticatedUsername());
        model.addAttribute("users", us.list().stream().filter(u -> !u.getUsername().equals(user.getUsername())));
        return "users/index";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        us.delete(id);
        return "redirect:/usuarios";
    }

    public String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return null;
    }
}
