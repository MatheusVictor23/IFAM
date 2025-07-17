package com.example.demo.DTO;

public class VoluntarioMissaoInputDTO {
    private String voluntario;
    private String missao;
    private String cidadeAtuacao;
    private String dataInicio;
    private String dataFim;
    private String avaliacao;
    private String parecer;

    public VoluntarioMissaoInputDTO() {}

    public VoluntarioMissaoInputDTO(String voluntario, String missao, String cidadeAtuacao, String dataInicio, String dataFim, String avaliacao, String parecer) {
        this.voluntario = voluntario;
        this.missao = missao;
        this.cidadeAtuacao = cidadeAtuacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.avaliacao = avaliacao;
        this.parecer = parecer;
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

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
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
