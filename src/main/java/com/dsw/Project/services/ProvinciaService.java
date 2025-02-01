package com.dsw.Project.services;

import com.dsw.Project.models.Provincia;
import com.dsw.Project.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class ProvinciaService implements com.dsw.Project.interfaces.ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Override
    public List<Provincia> list() {
        return provinciaRepository.findAll();
    }

    @Override
    public Provincia detail(int id) {
        return provinciaRepository.findById(id);
    }


}
