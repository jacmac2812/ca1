/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.NameDTO;
import entities.Name;
import facades.NameFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author jacobsimonsen
 */
@Path("names")
public class NameResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //får fejl på denne 
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final NameFacade FACADE = NameFacade.getNameFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    //får fejl på denne
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getNameCount() {
        long count = FACADE.getNameCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    //får fejl på denne 
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllNames() {
        List<Name> names = FACADE.getAllNames();
        List<NameDTO> nameDTOs = new ArrayList();
        for (Name name : names) {
            NameDTO nameDTO = new NameDTO(name);
            nameDTOs.add(nameDTO);
        }
        return GSON.toJson(nameDTOs);
    }

    //får fejl på denne
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getNameById(@PathParam("id") int id) {
        try {
            Name name = FACADE.getNameById(id);
            return GSON.toJson(name);
        } catch (NoResultException | NullPointerException e) {
            return GSON.toJson(null);
        }
    }
}
