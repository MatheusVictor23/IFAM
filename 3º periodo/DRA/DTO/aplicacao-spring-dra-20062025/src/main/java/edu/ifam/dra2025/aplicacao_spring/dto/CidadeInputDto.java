package edu.ifam.dra2025.aplicacao_spring.dto;

import edu.ifam.dra2025.aplicacao_spring.model.Cidade;
import edu.ifam.dra2025.aplicacao_spring.model.Estado;
import edu.ifam.dra2025.aplicacao_spring.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CidadeInputDto {

    private String nome;

    private String estado;

    public CidadeInputDto() {
    }

    public CidadeInputDto(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cidade build(EstadoRepository estadoRepository){
        Cidade cidade = new Cidade();
        cidade.setNome(getNome());

        Estado estado = estadoRepository.findBySigla(getEstado());

        cidade.setEstado(estado);

        return cidade;
    }
}
