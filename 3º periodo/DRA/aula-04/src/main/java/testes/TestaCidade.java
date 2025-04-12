package testes;

import model.Cidade;
import model.Pessoa;
import util.JPAUtil;

import javax.persistence.EntityManager;

public class TestaCidade {

    public void InserirPessoasNaCidade(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Pessoa pessoa01 = new Pessoa("Pessoa01", "Pessoa01@gmail.com", "923423424");
        Pessoa pessoa02 = new Pessoa("Pessoa02", "Pessoa02@gmail.com", "923345345");
        Pessoa pessoa03 = new Pessoa("Pessoa03", "Pessoa03@gmail.com", "923445644");

        entityManager.getTransaction().begin();

        Cidade cidade = entityManager.find(Cidade.class, 1L);

        cidade.getPessoas().add(pessoa01);
        cidade.getPessoas().add(pessoa02);
        cidade.getPessoas().add(pessoa03);

        entityManager.getTransaction().commit();
    }
    public static void main(String[] args) {}
}
