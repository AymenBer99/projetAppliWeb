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
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_CONNECTED = "connected";
    public static final String VUE = "/appli/gouvern.jsp";
    public static final String ACCEUIL = "/index.jsp";
	
    public static Boolean b = true;
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
        
		if (b) {
			MesuresGouvernementales mesure = new MesuresGouvernementales();
			mesure.setTitre("titre1");
			mesure.setTexte("texte1");
            mesure.setDate("2021-05-22");
            MesuresGouvernementales mesure2 = new MesuresGouvernementales();
			mesure2.setTitre("titre2");
			mesure2.setTexte("texte2");
            mesure2.setDate("2021-05-22");
            facade.addMesuregouv(mesure);
            facade.addMesuregouv(mesure2);
            b = false;
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
        
        
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
        } else {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	
        }
        
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        String mesuresString = Arrays.toString(mesures.toArray());
        request.setAttribute( ATT_MESURE, mesuresString.substring(1, mesuresString.length()-2).split(","));
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        	
    }

}