package edu.ifam.dra2025.aplicacao_spring.repository;

import edu.ifam.dra2025.aplicacao_spring.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado,Long> {

    Estado findBySigla(String sigla);

}
