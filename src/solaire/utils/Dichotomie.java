package solaire.utils;

import solaire.exception.PrecisionException;

public class Dichotomie {
    Double gauche;
    Double droite;
    Double centre;


    public Dichotomie(Double centre) {
        this.setCentre(centre);
    }


    public Double computeCentre(){
        Utils.getMethodInfo();

        return (this.droite + this.gauche) / 2;

    }


    public Double dichotomie(char s) throws PrecisionException{
        Utils.getMethodInfo();
        System.out.println("#### centre de dichotomie: "+this.centre);
        System.out.println("#### BORNE GAUCHE: "+this.gauche+ "         BORNE DROITE: "+this.droite);
        try {
            System.out.println("DIFFERENCE: "+ (this.droite - this.gauche));
            if((this.droite - this.gauche) <= Math.pow(10, -9))
                throw new PrecisionException("Limite de précision atteinte: " + (this.droite - this.gauche));
            
        } catch (Exception e) {
            if (e instanceof PrecisionException)
                throw e;
        }
        
        if(s == '-'){
            System.out.println("#### opération : GAUCHE ");
            this.droite = this.centre;
            if(this.gauche == null){
                System.out.println("#### opération : PLUS A GAUCHE GAUCHE ");
                this.setCentre(this.centre / 2);
            }
            else{
                this.setCentre(this.computeCentre());
            }

        }
        else if(s == '+'){
            System.out.println("#### opération : DROITE ");
            this.gauche = this.centre;
            if(this.droite == null){
                System.out.println("#### opération : PLUS A GAUCHE DROITE ");
                this.setCentre(this.centre * 2);
            } 
            else{
                this.setCentre(this.computeCentre());
            }
        }
        System.out.println("#### VALEUR TEMPORAIRE: "+this.centre);
        return this.centre;
    }


    public Double getGauche() {
        return gauche;
    }
    public void setGauche(Double gauche) {
        this.gauche = gauche;
    }
    public Double getDroite() {
        return droite;
    }
    public void setDroite(Double droite) {
        this.droite = droite;
    }
    public Double getCentre() {
        return centre;
    }
    public void setCentre(Double centre) {
        this.centre = centre;
    }


}
