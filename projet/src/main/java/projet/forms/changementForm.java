package projet.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import projet.beans.Utilisateur;
import projet.servlets.Facade;

public class changementForm {
    private static final String CHAMP_ANCIENMDP  = "mdp0";
    private static final String CHAMP_NEWMDP   = "mdp";
    private static final String CHAMP_CONFIRM  = "mdp1";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    
    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
	
	
    public void changerMdpUtilisateur( HttpServletRequest request, Facade facade) {
        String mdp0 = getValeurChamp( request, CHAMP_ANCIENMDP );
        String mdp = getValeurChamp( request, CHAMP_NEWMDP );
        String mdp1 = getValeurChamp( request, CHAMP_CONFIRM );

        HttpSession session = request.getSession();
        
        try {
        	validationMotsDePasse(((Utilisateur)session.getAttribute(ATT_SESSION_USER)).getEmail(), mdp0, facade);
        } catch(Exception e) {
        	setErreur(CHAMP_ANCIENMDP, e.getMessage());
        }
        
        try {
        	validationNouveauxMotsDePasse(mdp, mdp1);
        } catch(Exception e) {
        	setErreur(CHAMP_NEWMDP, e.getMessage());
        	setErreur(CHAMP_CONFIRM, e.getMessage());
        }
        
        /* Initialisation du r�sultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succ�s du changement.";
            facade.changeUserPassword(((Utilisateur)session.getAttribute(ATT_SESSION_USER)).getEmail(), mdp);;
        } else {
            resultat = "�chec de changement."+erreurs.toString();
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
			throw new Exception("Mot de passe Incorrect"+user.getMotDePasse()+"/"+motDePasse);   		
    	}
	}

	private void validationNouveauxMotsDePasse( String motDePasse, String confirmation ) throws Exception {
	    if ( motDePasse != null && confirmation != null ) {
	        if ( !motDePasse.equals( confirmation ) ) {
	            throw new Exception( "Les mots de passe entr�s sont diff�rents, merci de les saisir � nouveau." );
	        } else if ( motDePasse.length() < 8 ) {
	            throw new Exception( "Les mots de passe doivent contenir au moins 3 caract�res." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
	    }
	}
	
    /*
     * Ajoute un message correspondant au champ sp�cifi� � la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
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