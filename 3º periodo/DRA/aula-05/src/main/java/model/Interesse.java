package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Interesse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToMany(mappedBy = "interesses") //'interesses' é a lista de interesse já declarado no Model Pessoa.
    private List<Pessoa> pessoas;

    public Interesse() {}

    public Interesse(String nome){
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void addPessoa(Pessoa pessoa){
        pessoa.addInteresse(this); // Inserir o interesse na lista interesses de pessoa, pois pessoa é o "mandante", logo não usamos a lista pessoas aqui na classe
    }
}
