package solaire;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import solaire.utils.Utils;

import java.sql.*;

public class Meteo {
    Date jour;
    HashMap<Integer, Integer> luminosite;

    


    public Meteo() {
    }



    public Meteo(Date jour, HashMap<Integer, Integer> luminosite) {
        this.setJour(jour);
        this.setLuminosite(luminosite);
    }

    
    public static HashMap<Date, Meteo> getMap(Vector<Meteo>meteo){
        Utils.getMethodInfo();

        HashMap<Date, Meteo> res = new HashMap<Date, Meteo>();
        for(Meteo meteoCour : meteo){
            res.put(meteoCour.getJour(), meteoCour);
        }
        return res;
    }

    public static Vector<Meteo> getAll(Connection conn, Date lim) throws Exception{
        Utils.getMethodInfo();

        Vector<Meteo> meteos = new Vector<Meteo>();
        try {
            String sql = String.format("SELECT * FROM meteo where jour <= '%s'", lim);
            System.out.println("****    getMeteo: "+ sql);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Date date = null;
            HashMap<Integer, Integer> luminosite = null;
            Meteo meteo = null;
            while (rs.next()) {
                if(rs.getDate("jour").equals(date) == false){
                    date = rs.getDate("jour");
                    luminosite = new HashMap<Integer, Integer>();
                    meteo = new Meteo(date, luminosite);
                    meteos.add(meteo);

                }
                meteo.getLuminosite().put(rs.getInt("heure"), rs.getInt("indice"));
            }
        } catch (Exception e) {
            throw e;
        }
        return meteos;
    }

 
    
    public Date getJour() {
        return jour;
    }
    public void setJour(Date jour) {
        this.jour = jour;
    }
    public HashMap<Integer, Integer> getLuminosite() {
        return luminosite;
    }
    public void setLuminosite(HashMap<Integer, Integer> luminosite) {
        this.luminosite = luminosite;
    }

    @Override
    public String toString() {
        String ch ="";
        Set<Integer> keys = this.luminosite.keySet();
        for(Integer key: keys){
            ch += String.format("       Date: %s      Heure: %d      Lum: %d\n", this.jour, key, this.luminosite.get(key));

        }
        return ch;
    }
}
