package solaire;

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

    public void setReserve(double reserve) throws Exception {
        if(reserve > this.capacite || reserve < 0)
            throw new Exception("Valeur de reserve d'énergie impossible pour cette batterie: "+reserve);
        this.reserve = reserve;
    }

    public static void setInutilisable(double inutilisable) {
        Batterie.inutilisable = inutilisable;
    }
}
