package com.dsw.Project.services;

import com.dsw.Project.models.Cart;
import com.dsw.Project.models.Role;
import com.dsw.Project.models.User;
import com.dsw.Project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Primary
public class UserService implements com.dsw.Project.interfaces.UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CartService cartService;
    private
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<User> list() {
        return repository.findAll();
    }

    @Override
    public User detail(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void create(User user) throws Exception {
        user.setCart(new Cart());
        user.setRoles(new ArrayList<Role>());
        user.getRoles().add(roleService.findByName("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        cartService.createCart(user.getCart(), user);
        repository.saveAndFlush(user);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void delete(int id) {
            repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }
        };
        return userDetails;
    }

}
