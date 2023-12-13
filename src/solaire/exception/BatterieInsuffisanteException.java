package solaire.exception;

import solaire.Etat;

public class BatterieInsuffisanteException extends Exception {
    Etat etat;

    public BatterieInsuffisanteException(String msg, Etat etat){
        super(msg);
        this.setEtat(etat);
    }
    

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
