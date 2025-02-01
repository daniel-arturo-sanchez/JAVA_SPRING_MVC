package com.dsw.Project.repositories;

import com.dsw.Project.models.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    public Provincia findById(int id);
}
