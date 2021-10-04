package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@Path("person")
public class PersonResource {

    private final EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

    private final PersonFacade facade = PersonFacade.getPersonFacade(emf);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    public PersonResource() {
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<PersonDTO> query = em.createQuery("SELECT p FROM Person p", PersonDTO.class);
            List<PersonDTO> persons = query.getResultList();
            return new Gson().toJson(persons);
        } finally {
            em.close();
        }
    }
}