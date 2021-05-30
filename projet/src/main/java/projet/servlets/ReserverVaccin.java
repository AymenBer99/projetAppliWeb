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
    public static final String ATT_PROFILEVACC = "profileVacc";
    public static final String ATT_VACCRESERVE = "vaccinReserved";

    
    
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
        Utilisateur user = (Utilisateur) session.getAttribute(ATT_SESSION_USER);
		RendezvousVaccin r;
        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
            r = facade.associateVaccinToUser(Integer.parseInt(request.getParameter("vaccin")),(Utilisateur)session.getAttribute(ATT_SESSION_USER));
            String infoVaccin =  "<tr><td><p class=\"text-left\" style=\"color:white\">Heure :"+r.getHeure()+"</p></td></tr>"
            		+"<tr><td><p class=\"text-left\" style=\"color:white\">Centre :"+r.getCentre()+"</p></td></tr>"
            		+"<tr><td><p class=\"text-left\" style=\"color:white\">Vaccin :"+r.getVaccin()+"</p></td></tr>"
            		+"<tr><td><p class=\"text-left\" style=\"color:white\">Utilisateur :"+r.getPatient()+"</p></td></tr>";
            request.setAttribute(ATT_PROFILEVACC, infoVaccin);
            if (!user.isAdmin()) {
                request.setAttribute(ATT_VACCRESERVE, "<button class=\"btn btn-success margin-bottom center\" type=\"submit\" name=\"submit\" id=\"submit\">Vaccin reservé</button>");
            } 
        } else {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	
        }
                
        request.setAttribute( ATT_USER, session.getAttribute(ATT_SESSION_USER) );
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );	
	}
	

}
