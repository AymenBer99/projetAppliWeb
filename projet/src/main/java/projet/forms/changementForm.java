package projet.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import projet.beans.Utilisateur;
import projet.servlets.Facade;

public class changementForm {
    private static final String CHAMP_ANCIENMDP  = "mdp0";
    private static final String CHAMP_NEWMDP   = "mdp";
    private static final String CHAMP_CONFIRM  = "mdp1";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
	
	
    public void cchangerMdpUtilisateur( HttpServletRequest request, Facade facade) {
        String mdp0 = getValeurChamp( request, CHAMP_ANCIENMDP );
        String mdp = getValeurChamp( request, CHAMP_NEWMDP );
        String mdp1 = getValeurChamp( request, CHAMP_CONFIRM );

    }
    
    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotsDePasse( String email,String motDePasse, Facade facade) throws Exception {
    	Utilisateur user = facade.getUser(email);
    	if (user == null) {
			throw new Exception("Utilisateur non inscrit");
    	} else if(!user.getMotDePasse().equals(motDePasse)){
			throw new Exception("Mot de passe Incorrect");   		
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
