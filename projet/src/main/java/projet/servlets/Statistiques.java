package projet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
import projet.forms.StatistiquesForm;
import projet.forms.VaccinationForm;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Statistiques")
public class Statistiques extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private Facade facade;
    public static final String ATT_STAT = "stat";
    public static final String ATT_AJOUTSTAT = "ajoutstat";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_CONNECTED = "connected";
    public static final String VUE = "/appli/statistiques.jsp";
    public static final String ACCEUIL = "/index.jsp";
    public static final String ATT_ERREUR = "erreur";


    


    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statistiques() {
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
            	request.setAttribute( ATT_AJOUTSTAT, "<a class=\"nav-link center\" href=\"/projet/AjouterStat\">Ajouter statistiques</a>" );
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
        StatistiquesForm form = new StatistiquesForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        projet.beans.Statistiques stat = form.AffichageStatistiques(request,facade);

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
            if ( ((Utilisateur) session.getAttribute(ATT_SESSION_USER)).isAdmin()) {
            	request.setAttribute( ATT_AJOUTSTAT, "<a class=\"nav-link center\" href=\"/projet/AjouterStat\">Ajouter statistiques</a>" );
            }
        } else {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	
        }
        
        if (stat != null) {
            String statstring = stat.toString();
        request.setAttribute( ATT_STAT, statstring);
        request.setAttribute( ATT_ERREUR, "");
        }else{
        request.setAttribute( ATT_ERREUR, "<p class = \"center\">Aucun Statistique disponible pour cette date</p>");
         } 
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        	
    }

}