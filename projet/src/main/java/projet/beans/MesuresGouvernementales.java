package projet.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

@Entity
public class MesuresGouvernementales {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
    private int id;
    private String Titre;
    @Column(columnDefinition="varchar(1000)")
    private String Texte;
    private String Date;

    
    public MesuresGouvernementales(){
    	
    }
    
    public MesuresGouvernementales(Integer i, String p,String c,String d) {
    	this.id = i;
    	this.Titre = p;
        this.Texte = c;
        this.Date = d;  	
    }    
    
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
    
    public String toString() {
        return this.id+";"+this.getTitre()+";"+this.getTexte()+";"+this.getDate();
    }
}