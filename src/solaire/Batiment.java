package solaire;

import java.sql.Date;
import java.util.HashMap;

import solaire.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Batiment{
    int idBatiment;
    String nomBatiment;
    Source source;
    int elMatinee;
    int elAm;
    HashMap<Date, Integer> elMatineePrec = new HashMap<Date, Integer>();
    HashMap<Date, Integer> elAMPrec =new HashMap<Date, Integer>();

    
    public Batiment() {
    }


    public Batiment(int idBatiment, String nomBatiment) {
        this.idBatiment = idBatiment;
        this.nomBatiment = nomBatiment;
    }


    public int getElevesPrevus(Connection conn, Date date, String heure) throws Exception{
        Utils.getMethodInfo();
        int res = -1;
        try {
            //String sql = "SELECT elevesPresents(?, ?, ?)";
            String sql = String.format("select elevespresents('%s', %d, '%s')", date, idBatiment, heure);
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            //PreparedStatement stmt = conn.prepareStatement(sql);

            // Définir les paramètres
            //stmt.setDate(1, date);
            //stmt.setInt(2, this.idBatiment);
            //stmt.setString(3, heure);

            // Exécuter la requête SELECT
            ResultSet rs = stmt.executeQuery(sql);

            // Récupérer la valeur de retour
            if (rs.next()) {
                res = rs.getInt(1);
            }
        
            stmt.close();
         } catch (Exception e) {
            throw e;
        }
    System.out.println("#### AVANT REUTURN: "+res);
    return res;
    
    }

    
    public int getIdBatiment() {
        return idBatiment;
    }
    public void setIdBatiment(int idBatiment) {
        this.idBatiment = idBatiment;
    }
    public String getNomBatiment() {
        return nomBatiment;
    }
    public void setNomBatiment(String nomBatiment) {
        this.nomBatiment = nomBatiment;
    }
    public Source getSource() {
        return source;
    }
    public void setSource(Source source) {
        this.source = source;
    }
    public int getElMatinee() {
        return elMatinee;
    }
    public void setElMatinee(int elMatinee) {
        this.elMatinee = elMatinee;
    }
    public int getElAm() {
        return elAm;
    }
    public void setElAm(int elAm) {
        this.elAm = elAm;
    }
    public HashMap<Date, Integer> getElMatineePrec() {
        return elMatineePrec;
    }
    public void setElMatineePrec(HashMap<Date, Integer> elMatineePrec) {
        this.elMatineePrec = elMatineePrec;
    }
    public HashMap<Date, Integer> getElAMPrec() {
        return elAMPrec;
    }
    public void setElAMPrec(HashMap<Date, Integer> elAMPrec) {
        this.elAMPrec = elAMPrec;
    }

}