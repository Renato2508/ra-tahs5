import java.util.Vector;

import connect.Connect;
import jakarta.servlet.RequestDispatcher;
import solaire.*;
import solaire.exception.BatterieInsuffisanteException;
import solaire.utils.Utils;
import connect.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
            String daty = "2023-12-14";
            Date date = Date.valueOf(daty);

            try {

                Connect connect = new Connect();
                connect.getConnectionPostGresql();
                Connection con = connect.getConnection();
                
                //chargement des données meteo
                HashMap<Date, Meteo> meteoPrec = Meteo.getMap(Meteo.getAll(con, date));
                System.out.println("Récupération des données meteo: "+meteoPrec);

                
                // chargement des données des sources
                Vector<Source> sources = Source.getAll(con);
                System.out.println("Récupération des données des sources: "+sources);

                for(Source s : sources){
                    s.setListeEtat(s.generatePrevisions(date, 8, 17, meteoPrec, con));
                }

                con.close();

                for(Source s: sources){
                    for(Etat e : s.getListeEtat()){
                        System.out.println(e.toString());
                    }
                }
                 
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}

