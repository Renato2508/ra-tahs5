package solaire;

public class Panneau {
    int idPanneau;
    String nomPanneau;
    double puissance;
    
    
    public Panneau(int idPanneau, String nomPanneau, double puissance) {
        this.idPanneau = idPanneau;
        this.nomPanneau = nomPanneau;
        this.puissance = puissance;
    }



    public int getIdPanneau() {
        return idPanneau;
    }
    public void setIdPanneau(int idPanneau) {
        this.idPanneau = idPanneau;
    }
    public String getNomPanneau() {
        return nomPanneau;
    }
    public void setNomPanneau(String nomPanneau) {
        this.nomPanneau = nomPanneau;
    }
    public double getPuissance() {
        return puissance;
    }
    public void setPuissance(double puissance) {
        this.puissance = puissance;
    }
}
