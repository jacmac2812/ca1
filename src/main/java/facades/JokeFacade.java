package facades;

import entities.Joke;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author ChristianMadsen
 */
public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Joke createJoke(String theJoke, String reference, String type) {
        EntityManager em = emf.createEntityManager();
        Joke joke = new Joke(theJoke, reference, type);
        try {
            em.getTransaction().begin();
            em.persist(joke);
            em.getTransaction().commit();
            return joke;
        } finally {
            em.close();
        }
    }

    public List<Joke> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Joke> query
                    = em.createQuery("SELECT j FROM Joke j", Joke.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Joke getJokeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Joke joke = em.find(Joke.class, id);
            return joke;
        } finally {
            em.close();
        }
    }

    public List<Joke> getJokesByType(String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Joke> query
                    = em.createQuery("SELECT j FROM Joke j WHERE j.type = :type", Joke.class);
            query.setParameter("type", type);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Joke getRandomJoke() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> countQuery
                    = em.createQuery("SELECT COUNT(j) FROM Joke j", Long.class);
            long count = countQuery.getSingleResult();

            Random random = new Random();
            int randNumber = random.nextInt((int) count);

            TypedQuery<Joke> selectQuery
                    = em.createQuery("SELECT j FROM Joke j", Joke.class);
            selectQuery.setFirstResult(randNumber);
            selectQuery.setMaxResults(1);
            Joke joke = selectQuery.getSingleResult();
            return joke;
        } finally {
            em.close();
        }
    }

    //TODO Remove/Change this before use
    public long getJokeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long jokeCount = (long) em.createQuery("SELECT COUNT(j) FROM Joke j").getSingleResult();
            return jokeCount;
        } finally {
            em.close();
        }

    }
}
