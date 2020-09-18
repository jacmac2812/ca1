/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Name;

/**
 *
 * @author jacobsimonsen
 */
public class NameDTO {
    
    private int id;
    private String name; 

    public NameDTO(Name name) {
        this.id = name.getId();
        this.name = name.getName();
        
    }    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
