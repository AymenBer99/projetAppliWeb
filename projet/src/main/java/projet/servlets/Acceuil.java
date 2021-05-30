package projet.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projet.beans.MesuresGouvernementales;

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
    public static final String ATTRIBUT_MESURE1 = "mesure1";
    public static final String ATTRIBUT_MESURE2 = "mesure2";
    public static final String ATTRIBUT_MESURE3 = "mesure3";
    public static String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	public static boolean b = true;


    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	
    
    	
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
    	
        
        
        if (session.getAttribute(ATT_SESSION_USER) != null) {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
        } else {
            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	
        }
        
        
	    Collection<MesuresGouvernementales> mesures1 = facade.getMesuresgouv(date);
	    String mesuresString1 = Arrays.toString(mesures1.toArray());
	    if (!mesuresString1.equals("[]")) {
	    request.setAttribute( ATTRIBUT_MESURE1, mesuresString1.substring(1, mesuresString1.length()-2).split(","));  
	    } 
	    Collection<MesuresGouvernementales> mesures2 = facade.getMesuresgouv(dateJ_1(date));
	    String mesuresString2 = Arrays.toString(mesures2.toArray());
	    if (!mesuresString2.equals("[]")) {
		    request.setAttribute( ATTRIBUT_MESURE2, mesuresString2.substring(1, mesuresString2.length()-2).split(","));  
		    } 
	    Collection<MesuresGouvernementales> mesures3 = facade.getMesuresgouv(dateJ_2(date));
	    String mesuresString3 = Arrays.toString(mesures3.toArray());
	    if (!mesuresString3.equals("[]")) {
		    request.setAttribute( ATTRIBUT_MESURE3, mesuresString3.substring(1, mesuresString3.length()-2).split(","));  
		    }  
	    
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        
       
        
       

        
    }
    
    
    public String dateJ_1(String date) {
    	int[] tab = {5,7,8,10,12};
    	int[] tab2 = {2,4,6,9,11};
    	if (Integer.parseInt(date.split("-")[2]) != 1) {
    		return date.split("-")[0]+"-"+date.split("-")[1]+"-"+Integer.toString(Integer.parseInt(date.split("-")[2])-1);
    	} else if (Arrays.binarySearch(tab, Integer.parseInt(date.split("-")[1])) >= 0){
    		return date.split("-")[0]+"-"+(Integer.parseInt(date.split("-")[1])-1)+"-30";
    	} else if (Arrays.binarySearch(tab2, Integer.parseInt(date.split("-")[1])) >= 0){
    		return date.split("-")[0]+"-"+(Integer.parseInt(date.split("-")[1])-1)+"-31";
    	} else if (Integer.parseInt(date.split("-")[1]) == 1) {
    		return (Integer.parseInt(date.split("-")[0])-1)+"-12-31";
    	} else if (AnneeBissextile(Integer.parseInt(date.split("-")[0]))){
    		return date.split("-")[0]+"-2-29";
    	} else {
    		return date.split("-")[0]+"-2-28";
    	}
    }
    
    public String dateJ_2(String date) {
    	int[] tab = {5,7,8,10,12};
    	int[] tab2 = {2,4,6,9,11};
    	if (Integer.parseInt(date.split("-")[2]) > 2) {
    		return date.split("-")[0]+"-"+date.split("-")[1]+"-"+(Integer.parseInt(date.split("-")[2])-2);
    	} else if (Arrays.binarySearch(tab, Integer.parseInt(date.split("-")[1])) >= 0){
    		return date.split("-")[0]+"-"+(Integer.parseInt(date.split("-")[1])-1)+"-29";
    	} else if (Arrays.binarySearch(tab2, Integer.parseInt(date.split("-")[1])) >= 0){
    		return date.split("-")[0]+"-"+(Integer.parseInt(date.split("-")[1])-1)+"-30";
    	} else if (Integer.parseInt(date.split("-")[1]) == 1) {
    		return (Integer.parseInt(date.split("-")[0])-1)+"-12-30";
    	} else if (AnneeBissextile(Integer.parseInt(date.split("-")[0]))){
    		return date.split("-")[0]+"-2-28";
    	} else {
    		return date.split("-")[0]+"-2-27";
    	}
    }
    
    public boolean AnneeBissextile(int annee){
    	if(annee % 4 ==0 && annee % 100 != 0){
    		   return true;
    		}else if(annee % 400 == 0){
    		   return true;
    		}else {
     		   return false;
    		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();
	    	
	        if (session.getAttribute(ATT_SESSION_USER) != null) {
	            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Profile\">Profile</a>" );
	        } else {
	            request.setAttribute( ATT_CONNECTED, "<a class=\"nav-link\" href=\"/projet/Connexion\">Connexion/Inscription</a>" );	
	        }
	      
	     
	        
	        /* Affichage de la page d'inscription */
	        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
