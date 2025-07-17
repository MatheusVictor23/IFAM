package com.example.demo.repository;

import com.example.demo.model.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    Voluntario findByNome(String nome);

    Boolean existsByCPF(String CPF);

    Boolean existsByPassaporte(String passaporte);

    Boolean existsByEmail(String email);
}
