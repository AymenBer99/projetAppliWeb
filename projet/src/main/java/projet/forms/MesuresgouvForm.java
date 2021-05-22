package projet.forms;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import projet.beans.Utilisateur;
import projet.beans.MesuresGouvernementales;
import projet.beans.RendezvousVaccin;
import projet.servlets.Facade;

public class MesuresgouvForm {

    private static final String CHAMP_DATE   = "dateannonce";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Collection<MesuresGouvernementales> AffichageMesures( HttpServletRequest request, Facade facade) {
        /* Récupération des champs du formulaire */
        String date = getValeurChamp( request, CHAMP_DATE );

        Collection<MesuresGouvernementales> mesures;
        
        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            mesures = facade.getMesuresgouv(date);
        } else {
            resultat = "Échec de la connexion."+erreurs.toString();
            mesures = null;
        }

        return mesures;
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