package com.dsw.Project.services;

import com.dsw.Project.models.Role;
import com.dsw.Project.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RoleService implements com.dsw.Project.interfaces.RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role detail(int id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void create(Role role) {
        roleRepository.saveAndFlush(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
