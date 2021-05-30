
package projet.forms;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import projet.beans.MesuresGouvernementales;
import projet.beans.RendezvousVaccin;
import projet.beans.Utilisateur;
import projet.servlets.Facade;

public final class AjouterMesureGouvForm {
     public static final String CHAMP_TITRE = "titre";
     public static final String CHAMP_TEXT = "text";
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

     public MesuresGouvernementales ajoutermesure( HttpServletRequest request ,Facade facade) {
         String titre = getValeurChamp( request, CHAMP_TITRE );
         String text = getValeurChamp( request, CHAMP_TEXT );
         String date = getValeurChamp( request, CHAMP_DATE );

         
         MesuresGouvernementales mesure = new MesuresGouvernementales();
            
        
         
         mesure.setTitre(titre);
         mesure.setTexte(text);
         mesure.setDate(date);

         
         
         if ( erreurs.isEmpty() ) {
             resultat = "Succès de l'ajout.";
             facade.addMesure(mesure);
         } else {
             resultat = "Échec de l'ajout.";
         }
         return mesure;
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