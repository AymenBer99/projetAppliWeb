package projet.forms;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import projet.beans.Utilisateur;
import projet.servlets.Facade;

public class ConnexionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "mdp";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur( HttpServletRequest request, Facade facade) {
        /* Récupération des champs du formulaire */
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );


        Utilisateur utilisateur;
        
        /* Validation du champ email. */
        try {
            validationEmail( email,facade );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }

        /* Validation du champ mot de passe. */
        try {
            validationMotsDePasse(email,motDePasse,facade);
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
            utilisateur = facade.getUser(email);
        } else {
            resultat = "Échec de la connexion."+erreurs.toString();
            utilisateur = null;
        }

        return utilisateur;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationEmail( String email, Facade facade ) throws Exception {
    	if (facade.getUser(email) == null) {
    			throw new Exception("Utilisateur non inscrit");
    	}
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