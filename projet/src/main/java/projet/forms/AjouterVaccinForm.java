
package projet.forms;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import projet.beans.RendezvousVaccin;
import projet.beans.Utilisateur;
import projet.servlets.Facade;

public final class AjouterVaccinForm {
     public static final String CHAMP_HEURE = "heure";
     public static final String CHAMP_CENTRE = "centre";
     public static final String CHAMP_VACCIN = "vaccin";
     public static final String CHAMP_DATE = "date";
     private String resultat;
     private Map<String, String> erreurs = new HashMap<String, String>();

 	@PersistenceContext
 	EntityManager em;
     
     public String getResultat() {
         return resultat;
     }
     public Map<String, String> getErreurs() {
         return erreurs;
     }

     public RendezvousVaccin ajoutervaccin( HttpServletRequest request ,Facade facade) {
         String heure = getValeurChamp( request, CHAMP_HEURE );
         String centre = getValeurChamp( request, CHAMP_CENTRE );
         String vaccin = getValeurChamp( request, CHAMP_VACCIN );
         String date = getValeurChamp( request, CHAMP_DATE );

         
         RendezvousVaccin rdvVaccin = new RendezvousVaccin();
            
        
         
         rdvVaccin.setHeure(heure);
         rdvVaccin.setCentre(centre);
         rdvVaccin.setVaccin(vaccin);
         rdvVaccin.setDate(date);

         
         
         if ( erreurs.isEmpty() ) {
             resultat = "Succès de l'ajout.";
             facade.addVaccin(rdvVaccin);
         } else {
             resultat = "Échec de l'ajout.";
         }
         return rdvVaccin;
     }

	
	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur( String champ, String message ) {
	    erreurs.put( champ, message );
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}
}