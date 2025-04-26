package testes;

import model.Cidade;
import model.Pessoa;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TestaCidade {


    private static void inserirCidade(){

        Cidade cidade1 = new Cidade("Manaus");
        Cidade cidade2 = new Cidade("Boa Vista");

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();


        entityManager.persist(cidade1);
        entityManager.persist(cidade2);

        entityManager.getTransaction().commit();
    }

    private static void inserirPessoasNaCidade(){

        Pessoa pessoa1 = new Pessoa("Pessoa01","Email01","Telefone01");
        Pessoa pessoa2 = new Pessoa("Pessoa02","Email02","Telefone02");
        Pessoa pessoa3 = new Pessoa("Pessoa03","Email03","Telefone03");

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        Cidade cidade = entityManager.find(Cidade.class, 1L);

//        pessoa1.setCidade(cidade);
//        pessoa2.setCidade(cidade);
//        pessoa3.setCidade(cidade);

        cidade.addPessoa(pessoa1);
        cidade.addPessoa(pessoa2);
        cidade.addPessoa(pessoa3);

        cidade.setNome("Manaus Alterado");

        entityManager.persist(pessoa1);
        entityManager.persist(pessoa2);
        entityManager.persist(pessoa3);

        entityManager.getTransaction().commit();
    }
    private static void listarCidade(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query consulta = entityManager.createQuery("select c from Cidade c");

        List<Cidade> cidades = consulta.getResultList();

        for(Cidade c:cidades){
            System.out.println(c);
            if(c.getPessoas() != null){
                for(Pessoa p: c.getPessoas()){
                    System.out.println("\t"+p);
                }
            }
        }
    }

    public static void main(String[] args) {
//        inserirCidade();
        //inserirPessoasNaCidade();
        listarCidade();

    }

}