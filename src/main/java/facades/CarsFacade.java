/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author fh
 */
public class CarsFacade {
    
    private static CarsFacade instance;
    private static EntityManagerFactory emf;


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarsFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarsFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void deleteAllCars() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public long getCarsCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long carCount = (long) em.createQuery("SELECT COUNT(c) FROM Car c").getSingleResult();
            return carCount;
        } finally {
            em.close();
        }
    }

    public Car getCarsById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Car car = em.find(Car.class, id); 
            return car;
        } finally {
            em.close();
        }
    }

    public List<Car> getCarsByModel(String model) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Car.getByModel");
            query.setParameter("model", model);
            List<Car> carList = query.getResultList();
            return carList;
        } finally {
            em.close();
        }
    }

    public List<Car> getAllCars() {

        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Car.getAll");
            List<Car> cars = query.getResultList();
            return cars;
        } finally {
            em.close();
        }
    }

    public void populateDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Car(1997, "Ford", "E350", "Jacob", 3000));
            em.persist(new Car(2000, "Chevy", "Venture", "Jonas", 5000));
            em.persist(new Car(2000, "Chevy", "Venture", "Frederik", 4900));
            em.persist(new Car(1991, "Jeep", "Grand Cherokee", "Jacob", 4799));
            em.persist(new Car(2005, "Volvo", "V70", "Jonas", 44799));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
