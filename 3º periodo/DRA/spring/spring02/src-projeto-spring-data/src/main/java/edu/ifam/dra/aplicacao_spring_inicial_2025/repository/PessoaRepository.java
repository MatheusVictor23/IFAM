package edu.ifam.dra.aplicacao_spring_inicial_2025.repository;

import edu.ifam.dra.aplicacao_spring_inicial_2025.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
}
