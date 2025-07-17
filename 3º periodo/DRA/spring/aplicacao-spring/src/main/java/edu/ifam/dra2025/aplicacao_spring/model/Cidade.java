package edu.ifam.dra2025.aplicacao_spring.model;

import jakarta.persistence.*;

@Entity
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    private Estado estado;
}
