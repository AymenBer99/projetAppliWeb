package projet.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import projet.beans.Utilisateur;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/PhotoDeProfil")
@MultipartConfig( fileSizeThreshold = 1024 * 1024, 
maxFileSize = 1024 * 1024 * 5,
maxRequestSize = 1024 * 1024 * 5 * 5 )
public class PhotoDeProfil extends HttpServlet {
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
    public PhotoDeProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static final String IMAGES = "/pdp";
    
    public String uploadPath;
    
    /*
     * Si le dossier de sauvegarde de l'image n'existe pas, on demande sa cr�ation.
     */ 
    @Override
    public void init() throws ServletException {
        uploadPath = getServletContext().getRealPath( IMAGES );
        File uploadDir = new File( uploadPath );
        if ( ! uploadDir.exists() ) uploadDir.mkdir();
    }
       
    /*
     * R�cup�ration et sauvegarde du contenu de chaque image.
     */ 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        for ( Part part : request.getParts() ) {
            String fileName = getFileName( part );
            String fullPath = uploadPath + File.separator + fileName;
            part.write( fullPath );
        }
 HttpSession session = request.getSession();
		
        Utilisateur user = (Utilisateur) session.getAttribute(ATT_SESSION_USER);
        
        request.setAttribute( ATT_USER, session.getAttribute(ATT_SESSION_USER) );

        String infosUser =  "<tr><td><p class=\"text-left\" style=\"color:white\">Nom :"+user.getNom()+"</p></td></tr>"
        		+"<tr><td><p class=\"text-left\" style=\"color:white\">Pr�nom :"+user.getPrenom()+"</p></td></tr>"
        		+"<tr><td><p class=\"text-left\" style=\"color:white\">Date de Naissance :"+user.getDateDeNaissance()+"</p></td></tr>"
        		+"<tr><td><p class=\"text-left\" style=\"color:white\">Adresse mail :"+user.getEmail()+"</p></td></tr>"
        +"<tr><td><p class=\"text-center\"><u>  <a class=\"nav-link\" href=\"changementMdp\">Changer de Mot de passe ?</a></u></p></td></tr>";
        
        String infosAdmin =  "<tr><td><p class=\"text-left\" style=\"color:white\">Adresse mail :"+user.getEmail()+"</p></td></tr>"
        +"<tr><td><p class=\"text-center\"><u>  <a class=\"nav-link\" href=\"changementMdp\">Changer de Mot de passe ?</a></u></p></td></tr>";
        
        if (user.isAdmin()) {
        	request.setAttribute(ATT_PROFILE, infosAdmin);
        } else {
        	request.setAttribute(ATT_PROFILE, infosUser);
        }
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );	

    }

    /*
     * R�cup�ration du nom du fichier dans la requ�te.
     */
    private String getFileName( Part part ) {
        for ( String content : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( content.trim().startsWith( "filename" ) )
                return content.substring( content.indexOf( "=" ) + 2, content.length() - 1 );
        }
        return "Default.file";
    }

}
