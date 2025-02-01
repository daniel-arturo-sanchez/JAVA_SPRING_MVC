package com.dsw.Project.repositories;

import com.dsw.Project.models.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    public Municipio findById(int id);
}
