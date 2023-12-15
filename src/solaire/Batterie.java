package solaire;

import solaire.exception.OverchargeException;
import solaire.exception.UnderchargeException;
import solaire.utils.Utils;

public class Batterie {
    int idBatterie;
    String nomBatterie;
    double capacite;
    double reserve;
    static double inutilisable = 0.5;


    

    public Batterie(int idBatterie, String nomBatterie, double capacite) {
        this.idBatterie = idBatterie;
        this.nomBatterie = nomBatterie;
        this.capacite = capacite;
        this.reserve = this.capacite;
    }
    

    // methods

    public void charger(double energie){
        try {
            double nouvelleCharge = this.reserve + energie;
            this.setReserve(nouvelleCharge);

        } catch (OverchargeException oce) {
            try {
                
                this.setReserve(this.capacite);

            } catch (Exception e) {
            }
        }
        catch(UnderchargeException uce){

        }
    }

    public double getTempsRestant(double puissance){
        Utils.getMethodInfo();
        return getEnergieDispo()/puissance;
    }

    public double getReservePerc(){
        Utils.getMethodInfo();
        return (reserve/capacite) * 100;
    }

    public double getEnergieDispo(){
        Utils.getMethodInfo();
        return reserve - capacite * inutilisable;
    }
    
    
    public static double getInutilisable() {
        return inutilisable;
    }

    public int getIdBatterie() {
        return idBatterie;
    }
    public void setIdBatterie(int idBatterie) {
        this.idBatterie = idBatterie;
    }
    public String getNomBatterie() {
        return nomBatterie;
    }
    public void setNomBatterie(String nomBatterie) {
        this.nomBatterie = nomBatterie;
    }
    public double getCapacite() {
        return capacite;
    }
    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    public double getReserve() {
        return reserve;
    }

    public void setReserve(double reserve) throws UnderchargeException, OverchargeException {
        if(reserve > this.capacite )
            throw new OverchargeException("Surcharge détectée");
        
        else if(reserve < 0)
            throw new UnderchargeException("Charge négative détectée");
        this.reserve = reserve;
    }

    public static void setInutilisable(double inutilisable) {
        Batterie.inutilisable = inutilisable;
    }
}
