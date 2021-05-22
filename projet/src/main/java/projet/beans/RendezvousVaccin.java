package projet.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RendezvousVaccin {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String Patient;
    private String Centre;
    private String Date;
    private String Heure;
    private String Vaccin;

    
    public RendezvousVaccin() {
    	
    }
    
    public RendezvousVaccin(Integer i, String p,String c,String d,String h,String v) {
    	this.id = i;
    	this.Patient = p;
        this.Centre = c;
        this.Date = d;
        this.Heure = h;
        this.Vaccin = v;   	
    }
    
    public void setPatient(String patient) {
    this.Patient = patient;
    }
    public String getPatient() {
    return this.Patient;
    }

    public void setCentre(String centre) {
    this.Centre = centre;
    }
    public String getCentre() {
    return this.Centre;
    }

    public void setDate(String date) {
    this.Date = date;
    }
    public String getDate() {
    return this.Date;
    }

    public void setHeure(String heure) {
    this.Heure = heure;
    }
    public String getHeure() {
    return this.Heure;
    }

    public void setVaccin(String vaccin) {
    this.Vaccin = vaccin;
    }
    public String getVaccin() {
    return this.Vaccin;
    }
    
    public String toString() {
    	return this.id+";"+this.getHeure()+";"+this.getCentre()+";"+this.getVaccin();
    }
}
