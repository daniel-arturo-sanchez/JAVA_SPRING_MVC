package com.dsw.Project.services;

import com.dsw.Project.models.Municipio;
import com.dsw.Project.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class MunicipioService implements  com.dsw.Project.interfaces.MunicipioService {
    @Autowired
    private MunicipioRepository municipioRepository;

    public Municipio details(int id) {
        return municipioRepository.findById(id);
    }

    @Override
    public List<Municipio> list() {
        return municipioRepository.findAll();
    }
}
