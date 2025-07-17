package com.example.demo.repository;

import com.example.demo.model.Missao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissaoRepository extends JpaRepository<Missao, Long> {
    Missao findByNome(String nome);

}
