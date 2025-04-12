package testes;

import model.Cidade;
import model.Estado;
import model.Pessoa;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TestaEstado {
    private static void inserirEstadoeCidade(){

        Estado estado = new Estado("Amazonas", "AM");
        Cidade cidade = new Cidade("Manaus", estado);
        System.out.println(estado);

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(estado);
        entityManager.persist(cidade);

        entityManager.getTransaction().commit();
    }


    private static void inserirCidadeComEstadoExistente(){


        Cidade cidade = new Cidade();


        System.out.println(cidade);

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        Estado estado = entityManager.find(Estado.class, 1L);
        cidade.setNome("Manacapuru");
        cidade.setEstado(estado);
        entityManager.persist(cidade);

        entityManager.getTransaction().commit();
    }

    private void inserirCidadesNoEstado(){
        Cidade cidade1 = new Cidade("Manacapuru");
        Cidade cidade2 = new Cidade("Iranduba");

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();
            Estado estado = entityManager.find(Estado.class, 1L);

            entityManager.persist(cidade1);
            entityManager.persist(cidade2);

        entityManager.getTransaction().commit();
    }

    private static void listarEstado(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query consulta = entityManager.createQuery("select es from Estado es");

        List<Estado> estados = consulta.getResultList();

        for(Estado es: estados){
            System.out.println(es);
        }
    }

    private static void consultarEstado(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Estado estado = entityManager.find(Estado.class,1L);

        System.out.println(estado);
    }

    private static void alterarEstado(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Estado estado = entityManager.find(Estado.class,1L);

        entityManager.getTransaction().begin();

        estado.setNome("");
        estado.setSigla("");

        entityManager.getTransaction().commit();

        System.out.println(estado);
    }

    private static void removerEstado(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Estado estado = entityManager.find(Estado.class,1L);

        entityManager.getTransaction().begin();

        entityManager.remove(estado);

        entityManager.getTransaction().commit();

        System.out.println(estado);
    }

    public static void main(String[] args) {

//        inserirEstadoeCidade();
//        inserirCidadeComEstadoExistente();
//        consultarEstado();
//        alterarEstado);
//        removerPessoa();

    }
}
