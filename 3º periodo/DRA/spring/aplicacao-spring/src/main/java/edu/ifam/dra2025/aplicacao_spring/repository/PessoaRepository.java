package edu.ifam.dra2025.aplicacao_spring.repository;

import edu.ifam.dra2025.aplicacao_spring.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
