package testes;

import model.Cidade;
import model.Pessoa;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TestaPessoa {


//    private static void inserirPessoaECidade(){
//
//        Cidade cidade = new Cidade("Manaus","AM");
//        Pessoa pessoa = new Pessoa("Rogerio","rogerio@email.com","9112-1234");
//        pessoa.setCidade(cidade);
//
//        System.out.println(pessoa);
//
//        EntityManager entityManager = JPAUtil.getEntityManager();
//
//        entityManager.getTransaction().begin();
//
//
//        entityManager.persist(cidade);
//        entityManager.persist(pessoa);
//
//        entityManager.getTransaction().commit();
//    }


    private static void inserirPessoaComCidadeExistente(){

        Pessoa pessoa = new Pessoa("Rogerio","rogerio@email.com","9112-1234");


        System.out.println(pessoa);

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        Cidade cidade = entityManager.find(Cidade.class,1L);
        pessoa.setCidade(cidade);
        entityManager.persist(pessoa);

        entityManager.getTransaction().commit();
    }

    private static void listarPessoa(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query consulta = entityManager.createQuery("select p from Pessoa p");

        List<Pessoa> pessoas = consulta.getResultList();

        for(Pessoa p:pessoas){
            System.out.println(p);
        }
    }

    private static void consultarPessoa(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Pessoa pessoa = entityManager.find(Pessoa.class,1L);

        System.out.println(pessoa);
    }

    private static void alterarPessoa(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Pessoa pessoa = entityManager.find(Pessoa.class,2L);

        entityManager.getTransaction().begin();

        pessoa.setNome("Rogerio Alterado");
        pessoa.setEmail("rogerio.alterado@email.com");
        pessoa.setTelefone("9111-1234");

        Cidade cidade = entityManager.find(Cidade.class, 1L);
        pessoa.setCidade(cidade);

        entityManager.getTransaction().commit();

        System.out.println(pessoa);
    }

    private static void removerPessoa(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Pessoa pessoa = entityManager.find(Pessoa.class,1L);

        entityManager.getTransaction().begin();

        entityManager.remove(pessoa);

        entityManager.getTransaction().commit();

        System.out.println(pessoa);
    }

    public static void main(String[] args) {

       inserirPessoaComCidadeExistente();
//        listarPessoa();
//        consultarPessoa();
//       alterarPessoa();
//          removerPessoa();

    }

}