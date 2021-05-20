package projet.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projet.beans.Utilisateur;
import projet.forms.InscriptionForm;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	
	@EJB
	private Facade facade;
	
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/appli/inscription.jsp";
    public static final String CONNEXION = "/index.jsp";
    public static final String ATT_CONNECTED = "connected";


    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Création du formulaire */
        InscriptionForm form = new InscriptionForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Utilisateur utilisateur = form.inscrireUtilisateur(request,facade);
        
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
        

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
    	
        request.setAttribute( ATT_CONNECTED, "Connexion/Inscription" );	
        
        if (form.getErreurs().isEmpty()) {
            this.getServletContext().getRequestDispatcher( CONNEXION ).forward( request, response );       	
        } else {
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }        
    }
    
}
