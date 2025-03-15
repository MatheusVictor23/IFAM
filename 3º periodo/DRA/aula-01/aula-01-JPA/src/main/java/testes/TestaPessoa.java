package testes;

import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestaPessoa {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa("Rogerio", "Rogerio@gmail.com","92984535");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Banco02PU");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin(); //Abrir a transação

        entityManager.persist(pessoa); //Save

        entityManager.getTransaction().commit(); //Salvar a transação
    }
}
