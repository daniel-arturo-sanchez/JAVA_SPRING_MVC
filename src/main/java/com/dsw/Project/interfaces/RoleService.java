package com.dsw.Project.interfaces;

import com.dsw.Project.models.Role;

import java.util.List;

public interface RoleService {
    public Role detail(int id);

    public void create(Role role);

    public Role findByName(String name);
}
