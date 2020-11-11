/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctm.meetingschedueling.beans;

import ctm.meetingschedueling.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author catal
 */
@Stateless
@Named
public class PersonFacade extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }

    public List<Person> getPersons() {
        try {
            return em.createNamedQuery("Person.findAll").getResultList();
        }
        catch(Exception ex) {
            System.out.println("Exception: " + ex);
        }
        List<Person> x = new ArrayList<Person>();
        x.add(0, new Person(1, "Doru"));
        return x;
    }

    public void addPerson(int id, String name) {
          em.getTransaction().begin();
          Person p = new Person();
          p.setId(id);
          p.setName(name);
          em.getTransaction().commit();
          em.close();
          
    }
    
    
    
    
}
