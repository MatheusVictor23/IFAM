package dao;

import model.Pessoa;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PessoaDao {
    public void inserir(Pessoa pessoa){
        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin(); //Abrir a transação

        entityManager.persist(pessoa); //Save

        entityManager.getTransaction().commit(); //Salvar a transação
    }

    public List<Pessoa> listar(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query consulta = entityManager.createQuery("SELECT p FROM Pessoa p");
        List<Pessoa> pessoas = consulta.getResultList();

        return pessoas;
    }

    public Pessoa consultar(Long id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Pessoa pessoa = entityManager.find(Pessoa.class, id);

        return pessoa;
    }

    public void alterar(Pessoa pessoa){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Pessoa pessoaAlterada = entityManager.find(Pessoa.class, pessoa.getId());

        entityManager.getTransaction().begin();

        pessoaAlterada.setNome(pessoa.getNome());
        pessoaAlterada.setEmail(pessoa.getEmail());
        pessoaAlterada.setTelefone(pessoa.getTelefone());

        entityManager.getTransaction().commit();

    }

    public void remover(Long id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Pessoa pessoa = entityManager.find(Pessoa.class, id);

        entityManager.getTransaction().begin();

        entityManager.remove(pessoa);

        entityManager.getTransaction().commit();
    }
}
