package testes;

import model.Interesse;
import model.Pessoa;
import util.JPAUtil;

import javax.persistence.EntityManager;

public class TestaInteresse {

    private static void inserirInteresse(){
        Interesse interesse01 = new Interesse("Livros");
        Interesse interesse02 = new Interesse("Games");
        Interesse interesse03 = new Interesse("Música");

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(interesse01);
        entityManager.persist(interesse02);
        entityManager.persist(interesse03);

        entityManager.getTransaction().commit();
    }

    private static void inserirPessoa(){
        Pessoa pessoa01 = new Pessoa("Pedro", "pedro@gmail.com", "9324242324");
        Interesse interesse = new Interesse("Computadores");

        interesse.addPessoa(pessoa01);

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(interesse);
        entityManager.persist(pessoa01);

        entityManager.getTransaction().commit();
    }

    public static void main(String args[]){
//        inserirInteresse();
        inserirPessoa();
    }
}


