package projet.servlets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.beans.MesuresGouvernementales;
import projet.beans.RendezvousVaccin;
import projet.beans.Statistiques;
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
	
	
	public RendezvousVaccin associateVaccinToUser(int idVaccin,Utilisateur user) {
		RendezvousVaccin r = em.find(RendezvousVaccin.class,idVaccin);
		if ((r.getPatient() == null) && !(user.isAdmin())) {
		r.setPatient(user.getEmail());
		em.merge(r);
		}
		return r;
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
	
	public Collection<String> couleursVaccins(Collection<RendezvousVaccin> vaccins){
		Collection<String> couleurs = new ArrayList<String>();
		for(RendezvousVaccin rdv : vaccins){
			   if (rdv.getPatient() != null) {
				   couleurs.add("red");
			   }else {
				   couleurs.add("green");
			   }
		}
		return couleurs;
	}
	
	public Statistiques getStat(String date){
		return em.find(Statistiques.class, date);
	}
	
	public void addStat(projet.beans.Statistiques statistiques) {
		em.persist(statistiques);
		em.flush();
	}

	public Collection<Statistiques> getStatistiques() {
        return em.createQuery("SELECT NEW projet.beans.Statistiques(r.Date,r.NouveauCas,r.nbrDeces,r.nbrVaccins,r.nbrGuerison) from projet.beans.Statistiques r", Statistiques.class).getResultList();
	}
}