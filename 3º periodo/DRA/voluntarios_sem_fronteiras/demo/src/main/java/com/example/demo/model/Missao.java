package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Missao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(nullable = false, length = 255)
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pais pais;

    @OneToMany(mappedBy = "missao")
    private List<VoluntarioMissao> voluntarios_missoes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
