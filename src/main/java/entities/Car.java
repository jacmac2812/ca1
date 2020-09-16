/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car"),
@NamedQuery(name = "Car.getAll", query = "SELECT c FROM Car c"),
@NamedQuery(name = "Car.getByModel", query = "SELECT c FROM Car c WHERE c.model LIKE CONCAT('%',:model,'%')"),
@NamedQuery(name = "Car.getById", query = "SELECT c FROM Car c WHERE c.id = :id")
})

/**
 *
 * @author fh
 */
public class Car implements Serializable {
    private Long id;
    private int year;
    private String make;
    private String model;
    private String owner;
    private int price;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    

    public Long getId() {
        return id;
    }

    public Car(int year, String make, String model, String owner, int price) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.owner = owner;
        this.price = price;
    }

    public Car() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getmake() {
        return make;
    }

    public void setmake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
