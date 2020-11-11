/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctm.meetingschedueling.beans;

import ctm.meetingschedueling.Meeting;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author catal
 */
@Stateless
@Named
public class MeetingFacade extends AbstractFacade<Meeting> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MeetingFacade() {
        super(Meeting.class);
    }

    public List<Meeting> getMeetings() {
        return em.createNamedQuery("Meeting.findAll").getResultList();
    }
    
}
