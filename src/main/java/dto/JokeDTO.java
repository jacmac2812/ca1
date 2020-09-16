package dto;

import entities.Joke;

/**
 *
 * @author ChristianMadsen
 */
public class JokeDTO {
    
    private int id;
    private String theJoke;

    public JokeDTO(Joke joke) {
        this.id = joke.getId();
        this.theJoke = joke.getTheJoke();
    }    

    public int getId() {
        return id;
    }

    public String getTheJoke() {
        return theJoke;
    }
    
    
    
}
