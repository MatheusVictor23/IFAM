package com.example.demo.DTO;

public class MissaoOutputDTO {
    private String nome;
    private String descricao;
    private String pais;

    public MissaoOutputDTO() {}

    public MissaoOutputDTO(String nome, String descricao, String pais) {
        this.nome = nome;
        this.descricao = descricao;
        this.pais = pais;
    }

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
