package com.example.demo.repository;

import com.example.demo.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    Pais findByNome(String nome);
}
