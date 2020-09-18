package facades;

import entities.Joke;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author ChristianMadsen
 */
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;
    Joke j1;
    Joke j2;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = JokeFacade.getJokeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            j1 = facade.createJoke("Hvorfor var blondinen glad for, at samle et puzzlespil på 6 måneder? -fordi der stod 2-4 år", "Jonas", "Blondine");
            j2 = facade.createJoke("Alle børnene kom sikkert over havet undtagen Jannik han tog Titanic", "vitser-jokes.dk", "Alle børnene");
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(j1);
            em.persist(j2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(2, facade.getJokeCount(), "Expects two rows in the database");
    }

    @Test
    public void testGetJokes() {
        assertEquals(2, facade.getAllJokes().size(), "Expects two movies in the database");
    }

    @Test
    public void testGetJokesByType() {
        List<Joke> jokes = facade.getJokesByType(j1.getType());
        assertEquals(j1.getId(), jokes.get(0).getId(), "Expects the same id");
    }

    @Test
    public void testGetJokeById() {
        Joke joke = facade.getJokeById(j2.getId());
        assertEquals(j2.getTheJoke(), joke.getTheJoke(), "Expects the same joke");
    }

    @Test
    public void testGetRandomJoke() {
        Joke joke = facade.getRandomJoke();
        if (j1.getId().intValue() == joke.getId().intValue()) {
            assertEquals(j1.getTheJoke(), joke.getTheJoke(), "Expects the same joke, if this joke is selected");
        } else if (j2.getId().intValue() == joke.getId().intValue()) {
            assertEquals(j2.getTheJoke(), joke.getTheJoke(), "Expects the same joke, if this joke is selected");
        }
    }

}
