package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
public class VoluntarioMissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario;

    @ManyToOne
    @JoinColumn(name = "missao_id")
    private Missao missao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFim;

    @ManyToOne
    private Avaliacao avaliacao;

    @ManyToOne
    private Cidade cidadeAtuacao;

    @Column(nullable = false, length = 500)
    private String parecer;

    public VoluntarioMissao(){}

    public VoluntarioMissao(Voluntario voluntario, Missao missao, LocalDate dataInicio, LocalDate dataFim, Avaliacao avaliacao, Cidade cidadeAtuacao, String parecer) {
        this.voluntario = voluntario;
        this.missao = missao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.avaliacao = avaliacao;
        this.cidadeAtuacao = cidadeAtuacao;
        this.parecer = parecer;
    }

    public Cidade getCidadeAtuacao() {
        return cidadeAtuacao;
    }

    public void setCidadeAtuacao(Cidade cidadeAtuacao) {
        this.cidadeAtuacao = cidadeAtuacao;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public Missao getMissao() {
        return missao;
    }

    public void setMissao(Missao missao) {
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


    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }
}
