/*package projet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projet.beans.Utilisateur;

/**
 * Servlet implementation class Profile
 */
/*@WebServlet("/Profile")
public class ReserverVaccin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_CONNECTED = "connected";
    public static final String VUE = "/appli/profile.jsp";
    public static final String ACCEUIL = "/index.jsp";
    public static final String ATT_PROFILE = "profile";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
  /*  public ReserverVaccin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Récupération de la session depuis la requête */
  /*      HttpSession session = request.getSession();
		
        RendezvousVaccin vaccin = (RendezvousVaccin) session.getAttribute(ATT_SESSION_USER);
        
        request.setAttribute( ATT_USER, session.getAttribute(ATT_SESSION_USER) );

        String infosUser =  "<tr><td><p class=\"text-left\" style=\"color:white\">Nom :"+user.getNom()+"</p></td></tr>"
        		+"<tr><td><p class=\"text-left\" style=\"color:white\">Prénom :"+user.getPrenom()+"</p></td></tr>"
        		+"<tr><td><p class=\"text-left\" style=\"color:white\">Date de Naissance :"+user.getDateDeNaissance()+"</p></td></tr>"
        		+"<tr><td><p class=\"text-left\" style=\"color:white\">Adresse mail :"+user.getEmail()+"</p></td></tr>"
        +"<tr><td><p class=\"text-center\"><u>  <a class=\"nav-link\" href=\"changementMdp\">Changer de Mot de passe ?</a></u></p></td></tr>";
        
        String infosAdmin =  "<tr><td><p class=\"text-left\" style=\"color:white\">Adresse mail :"+user.getEmail()+"</p></td></tr>"
        +"<tr><td><p class=\"text-center\"><u>  <a class=\"nav-link\" href=\"changementMdp\">Changer de Mot de passe ?</a></u></p></td></tr>";
        
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );	
	}
*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
        request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	

        this.getServletContext().getRequestDispatcher( ACCEUIL ).forward( request, response );	
	}

}
*/
