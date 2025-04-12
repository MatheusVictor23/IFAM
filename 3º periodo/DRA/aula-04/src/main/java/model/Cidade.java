package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true, length = 100)
    private String nome;

    @ManyToOne
    private Estado estado;

    @OneToMany(mappedBy = "cidade")
    private List<Pessoa> pessoas;

    public Cidade() {
    }

    public Cidade(String nome) {
        this.nome = nome;
    }

    public Cidade(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ",nome='" + nome + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}