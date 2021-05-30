package projet.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projet.beans.RendezvousVaccin;
import projet.beans.Utilisateur;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/ReserverVaccin")
public class ReserverVaccin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private Facade facade;
	
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_CONNECTED = "connected";
    public static final String VUE = "/appli/reserverVaccin.jsp";
    public static final String ACCEUIL = "/index.jsp";
    public static final String ATT_PROFILE = "profile";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserverVaccin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
		RendezvousVaccin r;
        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
            r = facade.associateVaccinToUser(Integer.parseInt(request.getParameter("vaccin")),(Utilisateur)session.getAttribute(ATT_SESSION_USER));
            request.setAttribute("vaccinReserved", r.getId()+"/"+r.getPatient());
        } else {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	
        }
                
        request.setAttribute( ATT_USER, session.getAttribute(ATT_SESSION_USER) );
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );	
	}
	

}
