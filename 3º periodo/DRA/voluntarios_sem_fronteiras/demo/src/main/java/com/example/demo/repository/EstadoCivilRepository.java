package com.example.demo.repository;

import com.example.demo.model.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Long> {
    EstadoCivil findByEstadoCivil(String nome);
}
