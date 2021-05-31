package projet.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Utilisateur {

	@Id
    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String dateDeNaissance;
    private Boolean admin;
    private String path = "images/profil.jpg";
    
    public void setEmail(String email) {
    this.email = email;
    }
    public String getEmail() {
    return email;
    }

    public void setAdmin(Boolean a) {
    this.admin = a;
    }
    public Boolean isAdmin() {
    return this.admin;
    }
    
    public void setMotDePasse(String motDePasse) {
    this.motDePasse = motDePasse;
    }
    public String getMotDePasse() {
    return motDePasse;
    }

    public void setNom(String nom) {
    this.nom = nom;
    }
    public String getNom() {
    return nom;
    }

    public void setPrenom(String prenom) {
    this.prenom = prenom;
    }
    public String getPrenom() {
    return prenom;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
    this.dateDeNaissance = dateDeNaissance;
    }
    public String getDateDeNaissance() {
    return dateDeNaissance;
    }
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}