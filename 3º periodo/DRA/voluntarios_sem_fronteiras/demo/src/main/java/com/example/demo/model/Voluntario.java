package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Voluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String passaporte;

    @Column(unique = true, length = 11, nullable = false)
    private String CPF;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false, unique = true)
    private String telefone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String tipoSanguineo;

    @Column(nullable = false)
    private String profissao;

    @Column(nullable = false)
    private int anosExperiencia;

    @ManyToOne
    private Avaliacao situacaoSaude;

    @ManyToOne
    private Status status;

    @OneToMany(mappedBy = "voluntario")
    private List<VoluntarioMissao> voluntario_missoes;

//    @ManyToOne
//    private EstadoCivil estadoCivil;


    public Long getId() {
        return id;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status estado) {
        this.status = estado;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    public Avaliacao getSituacaoSaude() {
        return situacaoSaude;
    }

    public void setSituacaoSaude(Avaliacao situacaoSaude) {
        this.situacaoSaude = situacaoSaude;
    }

//    public EstadoCivil getEstadoCivil() {
//        return this.estadoCivil;
//    }

//    public void setEstadoCivil(EstadoCivil estadoCivil) {
//        this.estadoCivil = estadoCivil;
//    }
}

