package com.example.demo.DTO;

import java.time.LocalDate;

public class VoluntarioMissaoOutputDTO {
    private String voluntario;
    private String missao;
    private String cidadeAtuacao;
    private String estadoAtuacao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String avaliacao;
    private String parecer;

    public VoluntarioMissaoOutputDTO() {}


    public VoluntarioMissaoOutputDTO(String voluntario, String missao, String cidadeAtuacao, String estadoAtuacao, LocalDate dataInicio, LocalDate dataFim, String avaliacao, String parecer) {
        this.voluntario = voluntario;
        this.missao = missao;
        this.cidadeAtuacao = cidadeAtuacao;
        this.estadoAtuacao = estadoAtuacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.avaliacao = avaliacao;
        this.parecer = parecer;
    }

    public String getEstadoAtuacao() {
        return estadoAtuacao;
    }

    public void setEstadoAtuacao(String estadoAtuacao) {
        this.estadoAtuacao = estadoAtuacao;
    }

    public String getCidadeAtuacao() {
        return cidadeAtuacao;
    }

    public void setCidadeAtuacao(String cidadeAtuacao) {
        this.cidadeAtuacao = cidadeAtuacao;
    }

    public String getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(String voluntario) {
        this.voluntario = voluntario;
    }

    public String getMissao() {
        return missao;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }
}
