
package projet.forms;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import projet.beans.MesuresGouvernementales;
import projet.beans.RendezvousVaccin;
import projet.beans.Statistiques;
import projet.beans.Utilisateur;
import projet.servlets.Facade;

public final class AjouterStatForm {
    public static final String CHAMP_DATE = "date";
    public static final String CHAMP_NOUVEAUXCAS = "NouveauCas";
    public static final String CHAMP_NBRDECES = "NbrDeces";
     public static final String CHAMP_NBRVACCIN = "nbrVaccins";
     public static final String CHAMP_NBRGUERISON = "nbrGuerison";

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

     public Statistiques ajouterstat( HttpServletRequest request ,Facade facade) {
         String date = getValeurChamp( request, CHAMP_DATE );
         int NouveauCas = getValeurChampint( request, CHAMP_NOUVEAUXCAS );
         int NbrDeces = getValeurChampint( request, CHAMP_NBRDECES );
         int nbrVaccins = getValeurChampint( request, CHAMP_NBRVACCIN );
         int nbrGuerison = getValeurChampint( request, CHAMP_NBRGUERISON );
         Statistiques stat = new Statistiques();
        
         stat.setDate(date);
         stat.setNouveauCas(NouveauCas);
         stat.setNbrDeces(NbrDeces);
         stat.setNbrVaccin(nbrVaccins);
         stat.setNbrGuerison(nbrGuerison);
         


         
         
         if ( erreurs.isEmpty() ) {
             resultat = "Succès de l'ajout.";
             facade.addStat(stat);
         } else {
             resultat = "Échec de l'ajout.";
         }
         return stat;
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
	
	private static int getValeurChampint( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return 0;
	    } else {
	        return Integer.parseInt(valeur.trim());
	    }
	}
}