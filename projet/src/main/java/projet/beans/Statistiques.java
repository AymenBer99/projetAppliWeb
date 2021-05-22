package projet.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Statistiques {

    @Id
    private String Date;
    private int NouveauCas  ;
    private int nbrDeces;
    private int nbrVaccins;
    private int nbrGuerison;

    public void setNouveauCas(int cas) {
    this.NouveauCas = cas;
    }
    public int getNouveauCas() {
    return this.NouveauCas;
    }

    public void setNbrDeces(int deces) {
    this.nbrDeces = deces;
    }
    public int getNbrDeces() {
    return this.nbrDeces;
    }
    
    public void setNbrVaccin(int vaccins) {
    this.nbrVaccins = vaccins;
    }
    public int getNbrVaccin() {
    return this.nbrVaccins;
    }
    
    public void setNbrGuerison(int gueris) {
    this.nbrGuerison = gueris;
    }
    public int getNbrGuerison() {
    return this.nbrGuerison;
    }

    public void setDate(String date) {
    this.Date = date;
    }
    public String getDate() {
    return this.Date;
    }
}