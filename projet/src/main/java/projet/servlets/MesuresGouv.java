package projet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projet.beans.MesuresGouvernementales;
import projet.beans.RendezvousVaccin;
import projet.beans.Utilisateur;
import projet.forms.ConnexionForm;
import projet.forms.MesuresgouvForm;
import projet.forms.VaccinationForm;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/MesuresGouv")
public class MesuresGouv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private Facade facade;
    public static final String ATT_MESURE = "mesure";
    public static final String ATT_AJOUTMESURE = "ajoutmesure";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_CONNECTED = "connected";
    public static final String VUE = "/appli/gouvern.jsp";
    public static final String ACCEUIL = "/index.jsp";
    public static final String ATT_ERREUR = "erreur";

    


    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesuresGouv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		
        
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        
        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
            if ( ((Utilisateur) session.getAttribute(ATT_SESSION_USER)).isAdmin()) {
            	request.setAttribute( ATT_AJOUTMESURE, "<a class=\"nav-link center\" href=\"/projet/AjouterMesureGouv\">Ajouter mesure</a>" );
            }
        } else {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	
        }
        
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );	
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Création du formulaire */
        MesuresgouvForm form = new MesuresgouvForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Collection<MesuresGouvernementales> mesures = form.AffichageMesures(request,facade);
        String mesuresString = Arrays.toString(mesures.toArray());

        
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );

        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
            if ( ((Utilisateur) session.getAttribute(ATT_SESSION_USER)).isAdmin()) {
            	request.setAttribute( ATT_AJOUTMESURE, "<a class=\"nav-link center\" href=\"/projet/AjouterMesureGouv\">Ajouter mesure</a>" );
            }
        } else {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	
        }
        
        if ( (!mesuresString.equals("[]"))) {
        request.setAttribute( ATT_MESURE, mesuresString.substring(1, mesuresString.length()-2).split(","));
        request.setAttribute( ATT_ERREUR, "");
    	} else {
        request.setAttribute( ATT_ERREUR, "<p class = \"center\">Aucune mesure disponible pour cette date</p>");

    	}
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        	
    }

}