package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author ChristianMadsen
 */
@Entity
@NamedQuery(name = "Joke.deleteAllRows", query = "DELETE from Joke")
public class Joke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String theJoke;
    private String reference;
    private String type;

    public Joke(String theJoke, String reference, String type) {
        this.theJoke = theJoke;
        this.reference = reference;
        this.type = type;
    }

    
    public Joke() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheJoke() {
        return theJoke;
    }

    public void setTheJoke(String theJoke) {
        this.theJoke = theJoke;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

    
    
}
