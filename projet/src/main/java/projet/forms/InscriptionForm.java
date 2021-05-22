
package projet.forms;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import projet.beans.Utilisateur;
import projet.servlets.Facade;

public final class InscriptionForm {
     public static final String CHAMP_EMAIL = "email";
     public static final String CHAMP_PASS = "mdp";
     public static final String CHAMP_CONF = "mdp1";
     public static final String CHAMP_NOM = "nom";
     public static final String CHAMP_PRENOM = "prenom";
     public static final String CHAMP_NAISSANCE = "datedenaissance";
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

     public Utilisateur inscrireUtilisateur( HttpServletRequest request ,Facade facade) {
         String email = getValeurChamp( request, CHAMP_EMAIL );
         String motDePasse = getValeurChamp( request, CHAMP_PASS );
         String confirmation = getValeurChamp( request, CHAMP_CONF );
         String nom = getValeurChamp( request, CHAMP_NOM );
         String prenom = getValeurChamp( request, CHAMP_PRENOM );
         String datedenaissance = getValeurChamp( request, CHAMP_NAISSANCE );
         
         Utilisateur utilisateur = new Utilisateur();
            
         try {
             validationEmail( email );
         } catch ( Exception e ) {
             setErreur( CHAMP_EMAIL, e.getMessage() );
         }
         
         utilisateur.setEmail( email );
         
         try {
             validationMotsDePasse( motDePasse, confirmation );
         } catch ( Exception e ) {
             setErreur( CHAMP_PASS, e.getMessage() );
             setErreur( CHAMP_CONF, null );
         }
         
         utilisateur.setMotDePasse( motDePasse );
         
         try {
             validationNom( nom );
         } catch ( Exception e ) {
             setErreur( CHAMP_NOM, e.getMessage() );
         }
         utilisateur.setNom( nom );
         
         try {
             validationPrenom( prenom );
         } catch ( Exception e ) {
             setErreur( CHAMP_PRENOM, e.getMessage() );
         }
         utilisateur.setPrenom( nom );
         try {
             validationNaissance( datedenaissance );
         } catch ( Exception e ) {
             setErreur( CHAMP_NAISSANCE, e.getMessage() );
         }
         utilisateur.setDateDeNaissance( datedenaissance );
         
         utilisateur.setAdmin(false);
         
         if ( erreurs.isEmpty() ) {
             resultat = "Succès de l'inscription.";
             facade.addUser(utilisateur);
         } else {
             resultat = "Échec de l'inscription.";
         }
         return utilisateur;
     }

	private void validationEmail( String email ) throws Exception {
		String[] parts = email.split("@");
		if (parts == null) {
			throw new Exception( "Merci de saisir une adresse mail." );
	    } else if (parts[1] == null || parts.length != 2) {
	        throw new Exception( "Merci de saisir une adresse mail valide." );
	    }
	}

	private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {
	    if ( motDePasse != null && confirmation != null ) {
	        if ( !motDePasse.equals( confirmation ) ) {
	            throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
	        } else if ( motDePasse.length() < 8 ) {
	            throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
	    }
	}

	private void validationNom( String nom ) throws Exception {
	    if ( nom == null) {
	        throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
	    }
	}

	private void validationPrenom( String prenom ) throws Exception {
	    if ( prenom == null) {
	        throw new Exception( );
	    }
	}

	private void validationNaissance( String naissance ) throws Exception {
	        String[] parts = naissance.split("-");
	        if ( parts.length < 3 || parts[0] == null || parts[1] == null || parts[2] == null) {
	            throw new Exception( naissance );
	        } else if ( Integer.parseInt(parts[2])<0 || Integer.parseInt(parts[2]) > 31) {
	            throw new Exception( "Jour invalide." );
	        } else if ( Integer.parseInt(parts[1])<0 || Integer.parseInt(parts[1]) > 12) {
	            throw new Exception( "Mois invalide." );
	        } else if ( Integer.parseInt(parts[0]) <0 || Integer.parseInt(parts[0]) > 2021) {
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
	        return valeur.trim();
	    }
	}
}