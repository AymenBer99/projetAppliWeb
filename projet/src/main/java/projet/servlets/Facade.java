package projet.servlets;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.beans.MesuresGouvernementales;
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
	
	public void addVaccin(RendezvousVaccin vaccin) {
		  em.persist(vaccin);
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
	
	
	
	public void changeUserPassword(String email,String newMdp) {
		 Utilisateur user= (Utilisateur)em.find(Utilisateur.class , email);
		 user.setMotDePasse(newMdp);
		 em.merge(user);
		 //em.createQuery("update Utilisateur set motDePasse = '"+newMdp+"' where email="+email).executeUpdate();
	}
	
    public void addMesuregouv(MesuresGouvernementales mesure) {
        em.persist(mesure);
        em.flush();
    }
    public Collection<MesuresGouvernementales> getMesuresgouv(String date){
        return em.createQuery("SELECT NEW projet.beans.MesuresGouvernementales(r.id,r.Titre,r.Texte,r.Date) from MesuresGouvernementales r where r.Date = '"+date+"' ", MesuresGouvernementales.class).getResultList();
    }

	public void addMesure(MesuresGouvernementales mesure) {
		em.persist(mesure);
		em.flush();
	}
}