/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.AddressDTO;
import dtos.PersonDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entities.Address;
import entities.Person;
import entities.Phone;
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {

    public static void main(String[] args) throws PersonNotFoundException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);

        Person p1 = new Person("Mathias", "Jensen", "cph-mj839@cphbusiness.dk");
        Address a1 = new Address("Holsteinsgade 66", "4.12");
        Phone phone1 = new Phone("20439396", "Cell");
        p1.getAddress().getCityInfo();
        a1.addPerson(p1);
        p1.addPhone(phone1);
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(a1);
        em.getTransaction().commit();
        em.close();
    }
}
