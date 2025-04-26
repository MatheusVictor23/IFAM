package testes;

import model.Cidade;
import model.Interesse;
import model.Pessoa;
import model.Situacao;
import util.JPAUtil;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TestaPessoa {


    private static void inserirPessoaECidade(){

        Cidade cidade = new Cidade("Manaus");
        Pessoa pessoa = new Pessoa("Rogerio","rogerio@email.com","9112-1234");
        pessoa.setCidade(cidade);

        System.out.println(pessoa);

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();


        entityManager.persist(cidade);
        entityManager.persist(pessoa);

        entityManager.getTransaction().commit();
    }


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

    private static void inserirPessoaEInteresse(){
        Interesse interesse = new Interesse("Carros");

        Situacao situacao = Situacao.BOM;

        Pessoa pessoa = new Pessoa("carmine", "rogerio@gmail.com", "92 934853453",  Situacao.BOM );
        pessoa.addInteresse(interesse);

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(interesse);
        entityManager.persist(pessoa);
        entityManager.getTransaction().commit();
    }

    private static void inserirPessoaComInteresseExistente(){
        Pessoa pessoa = new Pessoa("João", "joao@email.com", "92 981242342");

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();

        Interesse interesse = entityManager.find(Interesse.class, 2L);
        pessoa.addInteresse(interesse);
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

        Cidade cidade = entityManager.find(Cidade.class,2L);
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

//        inserirPessoaComCidadeExistente();
//          alterarPessoa();
//        listarPessoa();
//        consultarPessoa();

//          removerPessoa();

        inserirPessoaEInteresse();
//        inserirPessoaComInteresseExistente();
    }

}