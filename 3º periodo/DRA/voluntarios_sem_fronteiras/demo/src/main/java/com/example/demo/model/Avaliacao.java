package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String avaliacao;

    @OneToMany(mappedBy = "situacaoSaude")
    private List<Voluntario> voluntarios;

    @OneToMany(mappedBy = "avaliacao")
    private List<VoluntarioMissao> voluntarioMissoes;

    public Avaliacao() {}

    public Avaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }
}
