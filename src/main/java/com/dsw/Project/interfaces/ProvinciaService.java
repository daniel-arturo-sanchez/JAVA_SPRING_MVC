package com.dsw.Project.interfaces;

import com.dsw.Project.models.Provincia;

import java.util.List;

public interface ProvinciaService {
    public List<Provincia> list();

    public Provincia detail(int id);
}
