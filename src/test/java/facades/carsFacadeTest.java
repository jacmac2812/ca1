package facades;

import utils.EMF_Creator;
import entities.RenameMe;
import entities.Car;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class carsFacadeTest {

    private static EntityManagerFactory emf;
    private static CarsFacade facade;
    private Car c = new Car(2005, "Volvo", "V70", "Frederik", 44799);
    private Car c2 = new Car(2000, "Chevy", "Venture", "Jonas", 5000);
    private Car c3 = new Car(1991, "Jeep", "Grand Cherokee", "Jacob", 4799);

    public carsFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = CarsFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.persist(c);
            em.persist(c2);
            em.persist(c3);
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
    public void testGetCarsCount() {
        assertEquals(3, facade.getCarsCount(), "Expects three rows in the database");
    }
    
    @Test
    public void testDeleteCars() {
        facade.deleteAllCars();
        assertEquals(0, facade.getCarsCount(), "Expects 0 rows in the database");
    }
    
    @Test
    public void testgetCarsById() {
        assertEquals(c.getmake(), facade.getCarsById(1).getmake(), "Expects Volvo");
    }
    @Test
    public void testgetCarsByModel() {
        assertEquals(c.getModel(), facade.getCarsByModel("V70").get(0).getModel(), "Expects V70");
    }
    

}
