package projet.servlets;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	
}