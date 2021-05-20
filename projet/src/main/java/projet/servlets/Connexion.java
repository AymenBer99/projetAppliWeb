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
import projet.forms.ConnexionForm;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private Facade facade;
	
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_CONNECTED = "connected";
    public static final String VUE = "/appli/signIn.jsp";
    public static final String ACCEUIL = "/index.jsp";

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Création du formulaire */
        ConnexionForm form = new ConnexionForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur(request,facade);
        
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        
        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
        }
        
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
            	
        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "Profile" );
        } else {
            request.setAttribute( ATT_CONNECTED, "Connexion/Inscription" );	
        }
		        
        if (form.getErreurs().isEmpty()) {
            this.getServletContext().getRequestDispatcher( ACCEUIL ).forward( request, response );       	
        } else {
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }   	
    }

}
