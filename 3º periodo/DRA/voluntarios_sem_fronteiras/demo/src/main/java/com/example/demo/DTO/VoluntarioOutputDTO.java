package com.example.demo.DTO;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class VoluntarioOutputDTO {
    private Long id;
    private String passaporte;
    private String cpf;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private int idade;
    private String telefone;
    private String email;
    private String tipoSanguineo;
    private String profissao;
    private int anosExperiencia;
    private String situacaoSaude;
    private String status;
//    private String estadoCivil;


    public VoluntarioOutputDTO(){}

    public VoluntarioOutputDTO(Long id, String passaporte, String cpf, String nomeCompleto, LocalDate dataNascimento, int idade, String telefone, String email, String tipoSanguineo, String profissao, int anosExperiencia, String situacaoSaude, String status) {
        this.id = id;
        this.passaporte = passaporte;
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
        this.tipoSanguineo = tipoSanguineo;
        this.profissao = profissao;
        this.anosExperiencia = anosExperiencia;
        this.situacaoSaude = situacaoSaude;
        this.status = status;
//        this.estadoCivil = estadoCivil;
    }

//    public String getEstadoCivil() {
//        return estadoCivil;
//    }
//
//    public void setEstadoCivil(String estadoCivil) {
//        this.estadoCivil = estadoCivil;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
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

    public String getSituacaoSaude() {
        return situacaoSaude;
    }

    public void setSituacaoSaude(String situacaoSaude) {
        this.situacaoSaude = situacaoSaude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String estado) {
        this.status = estado;
    }
}
