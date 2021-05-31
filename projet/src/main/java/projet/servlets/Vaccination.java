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

import projet.beans.RendezvousVaccin;
import projet.beans.Utilisateur;
import projet.forms.ConnexionForm;
import projet.forms.VaccinationForm;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Vaccination")
public class Vaccination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private Facade facade;
	
    public static final String ATT_VACCIN = "vaccins";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_CONNECTED = "connected";
    public static final String VUE = "/appli/vaccination.jsp";
    public static final String ACCEUIL = "/index.jsp";
    public static final String ATT_AJOUTVACCIN = "ajoutvaccins";
    public static final String ATT_COULEURSVACC = "couleursVacc";
    public static final String ATT_PROFILEVACC = "profileVacc";
    public static final String ATT_ERREUR = "erreur";


    

    
    public static Boolean vaccinCreated = false;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Vaccination() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		/*if (!vaccinCreated) {
			RendezvousVaccin vaccin = new RendezvousVaccin();
            vaccin.setDate("2021-05-22");
			vaccin.setVaccin("Pfizer");
			vaccin.setCentre("toulouse");
			vaccin.setHeure("14:12");
            facade.addRendezvousVaccin(vaccin);
			RendezvousVaccin vaccin2 = new RendezvousVaccin();
            vaccin2.setDate("2021-05-22");
			vaccin2.setVaccin("Moderna");
			vaccin2.setCentre("paris");
			vaccin2.setHeure("14:20");
            facade.addRendezvousVaccin(vaccin2);
			vaccinCreated = true;
		}*/
		 /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

		if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
            if ( ((Utilisateur) session.getAttribute(ATT_SESSION_USER)).isAdmin()) {
            	request.setAttribute( ATT_AJOUTVACCIN, "<a class=\"nav-link center\" href=\"/projet/AjouterVaccin\">Ajouter Vaccin</a>" );
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
        VaccinationForm form = new VaccinationForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Collection<RendezvousVaccin> vaccins = form.AffichageVaccins(request,facade);
        String messageVaccins = Arrays.toString(vaccins.toArray());
        
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        request.setAttribute( ATT_FORM, form );

        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
            if ( ((Utilisateur) session.getAttribute(ATT_SESSION_USER)).isAdmin()) {
            	request.setAttribute( ATT_AJOUTVACCIN, "<a class=\"nav-link center\" href=\"/projet/AjouterVaccin\">Ajouter Vaccin</a>" );
            }
        } else {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	
        }
       
        
        if ( (!messageVaccins.equals("[]"))) {
        Collection<String> couleurs = facade.couleursVaccins(vaccins);
 
        String couleursVaccins = Arrays.toString(couleurs.toArray());
        request.setAttribute( ATT_COULEURSVACC, couleursVaccins.substring(1, couleursVaccins.length()-1).split(","));
        

        /* Stockage du formulaire et du bean dans l'objet request */
       
        request.setAttribute( ATT_VACCIN, messageVaccins.substring(1, messageVaccins.length()-1).split(","));
        request.setAttribute( ATT_ERREUR, "");
        } else {
            request.setAttribute( ATT_ERREUR, "<p class = \"center\">Aucun vaccin disponible pour cette date</p>");

        }
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        	
    }

}