package projet.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Acceuil
 */
@WebServlet("/Acceuil")
public class Acceuil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private Facade facade;
	
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_CONNECTED = "connected";
    public static final String VUE = "/index.jsp";


    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	
        /* R�cup�ration de la session depuis la requ�te */
        HttpSession session = request.getSession();
    	
        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "Profile" );
        } else {
            request.setAttribute( ATT_CONNECTED, "Connexion/Inscription" );	
        }
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
