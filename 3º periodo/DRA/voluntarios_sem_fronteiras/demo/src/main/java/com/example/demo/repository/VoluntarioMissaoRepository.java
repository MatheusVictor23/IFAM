package com.example.demo.repository;

import com.example.demo.model.Voluntario;
import com.example.demo.model.VoluntarioMissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoluntarioMissaoRepository extends JpaRepository<VoluntarioMissao, Long> {
    List<VoluntarioMissao> findAllByVoluntario(Voluntario voluntario);
}
