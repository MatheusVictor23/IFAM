package testes;

import model.Cidade;
import model.Estado;
import model.Pessoa;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TestaEstado {


    private static void inserirEstado(){

        Estado estado1 = new Estado("Amazonas","AM");
        Estado estado2 = new Estado("Roraima","RR");


        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();


        entityManager.persist(estado1);
        entityManager.persist(estado2);

        entityManager.getTransaction().commit();
    }

    private static void inserirCidadesNoEstado(){

        Cidade cidade1 = new Cidade("Manacapuru");
        Cidade cidade2 = new Cidade("Iranduba");

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        Estado estado = entityManager.find(Estado.class, 1L);

//        entityManager.persist(cidade1);
        entityManager.persist(cidade2);

//        estado.getCidades().add(cidade1);
        estado.getCidades().add(cidade2);

        entityManager.getTransaction().commit();
    }
    private static void consultarEstado(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Estado estado = entityManager.find(Estado.class,1L);

        System.out.println(estado);

        System.out.println(estado.getCidades());
    }

    private static void listarEstados(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query consulta = entityManager.createQuery("select e from Estado e");

        List<Estado> estados = consulta.getResultList();

        for(Estado e:estados){
            System.out.println(e);
            if(e.getCidades() != null){
                for(Cidade c: e.getCidades()){
                    System.out.println("\t"+c);
                }
            }
        }
    }

    public static void main(String[] args) {
//        inserirEstado();
       listarEstados();
//        inserirCidadesNoEstado();
//        consultarEstado();
    }

}