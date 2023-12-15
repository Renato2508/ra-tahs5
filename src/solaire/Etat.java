
package solaire;

import java.sql.Date;
import java.sql.Time;

public class Etat {
    Source source;
    Time heure;
    Time heureFin;
    int nbrel;
    double demande;
    double demandePanneau;
    double demandeBatterie;
    double batterieInitiale;
    double batterieInitialePerc;
    double reserveBatterie;
    double reserveBatteriePerc;
    String status;
    Date date;

    

    

    /*public Etat(Source source, Time heure, double demande, double demandePanneau, double demandeBatterie,
            double reserveBatterie, double reserveBatteriePerc, String status) {
            
        this.source = source;
        this.heure = heure;
        this.demande = demande;
        this.demandePanneau = demandePanneau;
        this.demandeBatterie = demandeBatterie;
        this.reserveBatterie = reserveBatterie;
        this.reserveBatteriePerc = reserveBatteriePerc;
        this.status = status;
    }*/
    
    public Etat(Source source, Time heure, Time heureFin, double demande, double demandePanneau, double demandeBatterie,
            double batterieInitiale, double batterieInitialePerc, double reserveBatterie, double reserveBatteriePerc,
            String status, int nbrel) {
        this.source = source;
        this.heure = heure;
        this.heureFin = heureFin;
        this.demande = demande;
        this.demandePanneau = demandePanneau;
        this.demandeBatterie = demandeBatterie;
        this.batterieInitiale = batterieInitiale;
        this.batterieInitialePerc = batterieInitialePerc;
        this.reserveBatterie = reserveBatterie;
        this.reserveBatteriePerc = reserveBatteriePerc;
        this.status = status;
        this.nbrel = nbrel;
    }

    
    public Source getSource() {
        return source;
    }
    public void setSource(Source source) {
        this.source = source;
    }
    public Time getHeure() {
        return heure;
    }
    public void setHeure(Time heure) {
        this.heure = heure;
    }
    public double getDemande() {
        return demande;
    }
    public void setDemande(double demande) {
        this.demande = demande;
    }
    public double getDemandePanneau() {
        return demandePanneau;
    }
    public void setDemandePanneau(double demandePanneau) {
        this.demandePanneau = demandePanneau;
    }
    public double getDemandeBatterie() {
        return demandeBatterie;
    }
    public void setDemandeBatterie(double demandeBatterie) {
        this.demandeBatterie = demandeBatterie;
    }
    public double getReserveBatterie() {
        return reserveBatterie;
    }
    public void setReserveBatterie(double reserveBatterie) {
        this.reserveBatterie = reserveBatterie;
    }
    public double getReserveBatteriePerc() {
        return reserveBatteriePerc;
    }
    public void setReserveBatteriePerc(double reserveBatteriePerc) {
        this.reserveBatteriePerc = reserveBatteriePerc;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public double getBatterieInitiale() {
        return batterieInitiale;
    }

    public void setBatterieInitiale(double batterieInitiale) {
        this.batterieInitiale = batterieInitiale;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }
    public double getBatterieInitialePerc() {
        return batterieInitialePerc;
    }
    public void setBatterieInitialePerc(double batterieInitialePerc) {
        this.batterieInitialePerc = batterieInitialePerc;
    }

    @Override
    public String toString() {
        return String.format("####### SOURCE : %s       DATE: %s        DEMANDE MOY: %f     NBR: %d     Debut: %s     Fin: %s     Demande: %f     sur Panneau: %f      sur batterie: %f       Batterie Initiale: %f(%f %%)        Batterie Finale: %f (%f %%)   STATUS: %s \n",this.source.getNomSource(),this.getDate(),this.source.getDemandeMoy(),  this.source.getNbrEleves(), this.heure, this.heureFin, this.demande,  this.demandePanneau, this.demandeBatterie, this.batterieInitiale, this.batterieInitialePerc, this.reserveBatterie, this.reserveBatteriePerc, this.status );            
    }


    public int getNbrel() {
        return nbrel;
    }


    public void setNbrel(int nbrel) {
        this.nbrel = nbrel;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }
}
