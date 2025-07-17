package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class EstadoCivil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String estadoCivil;

//    @OneToMany(mappedBy = "estadoCivil")
//    private List<Voluntario> voluntarios;

    public EstadoCivil() {}

    public EstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
}
