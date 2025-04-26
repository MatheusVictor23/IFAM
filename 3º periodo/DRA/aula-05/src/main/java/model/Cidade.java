package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true, length = 100)
    private String nome;

    @OneToMany(mappedBy = "cidade")
    private List<Pessoa> pessoas = new ArrayList<>();

    public Cidade() {
    }

    public Cidade(String nome) {
        this.nome = nome;
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
        pessoa.setCidade(this);
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ",nome='" + nome + '\'' +
                '}';
    }
}