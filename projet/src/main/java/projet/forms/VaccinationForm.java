package projet.forms;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import projet.beans.Utilisateur;
import projet.beans.RendezvousVaccin;
import projet.servlets.Facade;

public class VaccinationForm {

    private static final String CHAMP_DATE   = "datevaccins";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Collection<RendezvousVaccin> AffichageVaccins( HttpServletRequest request, Facade facade) {
        /* Récupération des champs du formulaire */
        String date = getValeurChamp( request, CHAMP_DATE );

        Collection<RendezvousVaccin> Vaccins;
        
        /* Validation du champ email. */
        try {
            validationDate(date);
        } catch ( Exception e ) {
            setErreur( CHAMP_DATE, e.getMessage() );
        }

        
        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            Vaccins = facade.getVaccins(date);
        } else {
            resultat = "Échec de la connexion."+erreurs.toString();
            Vaccins = null;
        }

        return Vaccins;
    }


    private void validationDate( String date) throws Exception {
        String[] parts = date.split("-");
        if ( parts.length < 3 || parts[0] == null || parts[1] == null || parts[2] == null) {
            throw new Exception( "Format invalide");
        } else if ( Integer.parseInt(parts[2])<0 || Integer.parseInt(parts[2]) > 31) {
            throw new Exception( "Jour invalide." );
        } else if ( Integer.parseInt(parts[1])<0 || Integer.parseInt(parts[1]) > 12) {
            throw new Exception( "Mois invalide." );
        } else if ( Integer.parseInt(parts[0]) <0 || Integer.parseInt(parts[0]) < 1900) {
            throw new Exception( "Année invalide." );
        }
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
            return valeur;
        }
    }
}