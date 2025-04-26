package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="pessoa_nome") // passar um nome para o campo na tabela
    private String nome;
    private String email;
    private String telefone;

    @Enumerated(EnumType.ORDINAL)
    Situacao situacao;

    @ManyToOne
    private Cidade cidade;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="pessoa_interesse",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name="interesse_id")
    ) // Específicar o nome da tabela e dos campos, não é necessário para criar o relacionamento
    private List<Interesse> interesses = new ArrayList<>();

    public Pessoa() {
    }

    public Pessoa(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Pessoa(String nome, String email, String telefone,  Situacao situacao) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.situacao = situacao;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Interesse> getInteresse() {
        return interesses;
    }

    public void addInteresse(Interesse interesse) {
        this.interesses.add(interesse);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cidade='" + (cidade != null? cidade.getNome() : "") + '\'' +
                '}';
    }
}