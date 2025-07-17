package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sigla;

    @OneToMany(mappedBy = "pais")
    private List<Estado> estados;

    public Pais() {

    }

    public Pais(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
        this.estados = estados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(Estado estado) {
        this.estados.add(estado);
    }
}
