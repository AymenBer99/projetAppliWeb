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
import projet.forms.changementForm;

/**
 * Servlet implementation class changementMdp
 */
@WebServlet("/changementMdp")
public class changementMdp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private Facade facade;
	
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_CONNECTED = "connected";
    public static final String VUE = "/appli/changementMdp.jsp";
    public static final String ACCEUIL = "/index.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changementMdp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* R�cup�ration de la session depuis la requ�te */
        HttpSession session = request.getSession();
    	
        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
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
	       /* Cr�ation du formulaire */
        changementForm form = new changementForm();
		
        /* Appel au traitement et � la validation de la requ�te, et r�cup�ration du bean en r�sultant */
        form.changerMdpUtilisateur(request,facade);
        
        /* R�cup�ration de la session depuis la requ�te */
        HttpSession session = request.getSession();
            	
        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
        } else {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	
        }
		        
        if (form.getErreurs().isEmpty()) {
            this.getServletContext().getRequestDispatcher( ACCEUIL ).forward( request, response );       	
        } else {
        	request.setAttribute("motdepasse", form.getErreurs());
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }   	
	}

}