package com.dsw.Project.Config;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Role;
import com.dsw.Project.models.User;
import com.dsw.Project.repositories.RoleRepository;
import com.dsw.Project.repositories.UserRepository;
import com.dsw.Project.services.CartService;
import com.dsw.Project.services.RoleService;
import com.dsw.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository RoleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role roleUser = roleService.findByName("USER");
        if (roleUser == null) {
            roleUser = new Role();
            roleUser.setName("USER");
            RoleRepository.saveAndFlush(roleUser);
        }

        Role roleManager = roleService.findByName("MANAGER");
        if (roleManager == null) {
            roleManager = new Role();
            roleManager.setName("MANAGER");
            RoleRepository.saveAndFlush(roleManager);
        }

        Role roleAdmin = roleService.findByName("ADMIN");
        if (roleAdmin == null) {
            roleAdmin = new Role();
            roleAdmin.setName("ADMIN");
            RoleRepository.saveAndFlush(roleAdmin);
        }

        if (userService.findByUsername("user@myikea.com") == null) {
            User user = new User();
            user.setName("User");
            user.setUsername("user@myikea.com");
            user.setPassword(passwordEncoder.encode("Qwer123!"));
            user.setRoles(new ArrayList<Role>());
            user.getRoles().add(roleService.findByName("USER"));
            Cart userCart = new Cart();
            cartService.createCart(userCart, user);
            userRepository.saveAndFlush(user);
        }

        if (userService.findByUsername("manager@myikea.com") == null) {
            User manager = new User();

            manager.setName("Manager");
            manager.setUsername("manager@myikea.com");
            manager.setPassword(passwordEncoder.encode("Qwer123!"));
            manager.setRoles(new ArrayList<Role>());
            manager.getRoles().add(roleService.findByName("MANAGER"));
            Cart userCart = new Cart();
            cartService.createCart(userCart, manager);
            userRepository.saveAndFlush(manager);
        }

        if (userService.findByUsername("admin1@myikea.com") == null) {
            User admin1 = new User();

            admin1.setName("Admin1");
            admin1.setUsername("admin1@myikea.com");
            admin1.setPassword(passwordEncoder.encode("Qwer123!"));
            admin1.setRoles(new ArrayList<Role>());
            admin1.getRoles().add(roleService.findByName("ADMIN"));
            Cart userCart = new Cart();
            cartService.createCart(userCart, admin1);
            userRepository.saveAndFlush(admin1);
        }

        if (userService.findByUsername("admin2@myikea.com") == null) {
            User admin2 = new User();
            admin2.setName("Admin2");
            admin2.setUsername("admin2@myikea.com");
            admin2.setPassword(passwordEncoder.encode("Qwer123!"));
            admin2.setRoles(new ArrayList<Role>());
            admin2.getRoles().add(roleService.findByName("MANAGER"));
            admin2.getRoles().add(roleService.findByName("ADMIN"));
            Cart userCart = new Cart();
            cartService.createCart(userCart, admin2);
            userRepository.saveAndFlush(admin2);
        }
    }
}
