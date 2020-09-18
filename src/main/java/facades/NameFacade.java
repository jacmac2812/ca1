/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Name;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author jacobsimonsen
 */
public class NameFacade {

    private static NameFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private NameFacade() {}

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static NameFacade getNameFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new NameFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //får fejl på denne
    public Name createName(String name1, String studentId, String favoritSerie) {
        EntityManager em = emf.createEntityManager();
        Name name = new Name(name1, studentId, favoritSerie);
        try {
            em.getTransaction().begin();
            em.persist(name);
            em.getTransaction().commit();
            return name;
        } finally {
            em.close();
        }
    }

    public List<Name> getAllNames() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Name> query
                    = em.createQuery("SELECT n FROM Name n", Name.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Name getNameById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Name name = em.find(Name.class, id);
            return name;
        } finally {
            em.close();
        }
    }
   
    //TODO Remove/Change this before use
    public long getNameCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long nameCount = (long) em.createQuery("SELECT COUNT(n) FROM Name n").getSingleResult();
            return nameCount;
        } finally {
            em.close();
        }

    }

}
