package projet.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MesuresGouvernementales {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
    private int id;
    private String Titre;
    private String Texte;
    private String Date;

    public void setId(int id) {
    this.id = id;
    }
    public int getId() {
    return this.id;
    }
    
    public void setTitre(String titre) {
    this.Titre = titre;
    }
    public String getTitre() {
    return this.Titre;
    }

    public void setTexte(String texte) {
    this.Texte = texte;
    }
    public String getTexte() {
    return this.Texte;
    }

    public void setDate(String date) {
    this.Date = date;
    }
    public String getDate() {
    return this.Date;
    }
}