package projet.servlets;

import java.io.IOException;

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
import projet.forms.AjouterMesureGouvForm;
import projet.forms.AjouterStatForm;
import projet.forms.AjouterVaccinForm;
import projet.forms.InscriptionForm;

/**
 * Servlet implementation class AjouterVaccin
 */
@WebServlet("/AjouterStat")
public class AjouterStat extends HttpServlet {
	@EJB
	private Facade facade;
	
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/appli/AjouterStat.jsp";
    public static final String CONNEXION = "/appli/statistiques.jsp";
    public static final String ATT_CONNECTED = "connected";
    public static final String ATT_AJOUTSTAT = "ajoutstat";



    


    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
        } else {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	
        }      
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Création du formulaire */
        AjouterStatForm form = new AjouterStatForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        projet.beans.Statistiques stat = form.ajouterstat(request,facade);
        
		
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
        if (form.getErreurs().isEmpty()) {
            this.getServletContext().getRequestDispatcher( CONNEXION ).forward( request, response );  
        } else {
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }        
    }
    
}
