package com.dsw.Project.interfaces;

import com.dsw.Project.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> list();

    public User detail(int id);

    public void create(User role) throws Exception;

    public void delete(int id);
}
