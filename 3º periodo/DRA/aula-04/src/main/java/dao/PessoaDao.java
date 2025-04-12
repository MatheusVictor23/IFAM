package dao;

import exception.DaoException;
import model.Pessoa;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PessoaDao {

    public void inserir(Pessoa pessoa) throws DaoException {

        try {

            EntityManager entityManager = JPAUtil.getEntityManager();

            entityManager.getTransaction().begin();

            entityManager.persist(pessoa);

            entityManager.getTransaction().commit();
        }catch(Exception e){
            throw new DaoException(e);
        }
    }

    public List<Pessoa> listar(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query consulta = entityManager.createQuery("select p from Pessoa p");

        List<Pessoa> pessoas = consulta.getResultList();

        return pessoas;
    }

    public Pessoa consultar(Long id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Pessoa pessoa = entityManager.find(Pessoa.class,id);

        return pessoa;
    }

    public void alterar(Pessoa pessoa){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Pessoa pessoaAlterada = entityManager.find(Pessoa.class,pessoa.getId());

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