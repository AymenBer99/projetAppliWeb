package projet.servlets;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.beans.RendezvousVaccin;
import projet.beans.Utilisateur;

@Singleton
public class Facade {
	
	@PersistenceContext
	private EntityManager em;

	
	public void addUser(Utilisateur user) {
		  em.persist(user);
		  em.flush();
	}
	
	public Utilisateur getUser(String email) {
		return em.find(Utilisateur.class, email);
	}
	public void addRendezvousVaccin(RendezvousVaccin vaccin) {
        em.persist(vaccin);
        em.flush();
  }
	public Collection<RendezvousVaccin> getVaccins(String date){
        return em.createQuery("SELECT NEW projet.beans.RendezvousVaccin(r.id,r.Patient,r.Centre,r.Date,r.Heure,r.Vaccin) from RendezvousVaccin r where r.Date = '"+date+"' ", RendezvousVaccin.class).getResultList();
    }
	
	public Boolean verifierMdpCorrect(Utilisateur u, String mdp) {
		return true;
	}
}