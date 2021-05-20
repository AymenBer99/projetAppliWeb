package projet.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RendezvousVaccin {

    @Id
    private String Patient;
    private String Centre;
    private String Date;
    private String Heure;
    private String Vaccin;

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
}