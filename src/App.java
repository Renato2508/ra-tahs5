import java.util.Vector;

import connect.Connect;
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
        System.out.println("Cette fois les femmes ne font pas la pluie et le beau temps!");
        Utils.getMethodInfo();
        Connect connect = new Connect();
        connect.getConnectionPostGresql();
        Connection con = connect.getConnection();

        Date date = new Date(2023,11,14);
        HashMap<Integer, Integer> lum = new HashMap<Integer, Integer>();
        lum.put(8, 2);
        lum.put(9, 2);
        lum.put(10, 5);
        lum.put(11, 7);
        lum.put(12, 8);
        lum.put(13, 7);
        lum.put(14, 9);
        lum.put(15, 8);
        lum.put(16, 7);
        lum.put(17, 6);

        Meteo meteo = new Meteo(date, lum);

        Vector<Source> sources = Source.getAll(con);
        HashMap<Date, Meteo> meteoPrec = Meteo.getMap(Meteo.getAll(con));

        /*Set<Date> keys = meteoPrec.keySet();
        for(Date key: keys){
            System.out.println("CLE: "+key.getClass().getName());
            System.out.println(meteoPrec.get(key).toString());
        }*/

        date = new Date(123, 11, 7);
        System.out.println(date);
        Meteo meteo2 = meteoPrec.get(date);
        System.out.println("DONNEES METEO: \n"+meteo2.toString());

        
        
        Source source= sources.get(0);
        source.getDemandeMoy(date, meteo2);
        System.out.println(">>>>>> HEURE COUPURE CE JOUR LA: "+ source.getCoupuresPrec().get(date));

        Vector<Etat> etats = source.getListeEtat(8, 17, meteo2);
        System.out.println(etats.get(etats.size()-1).toString());
        //source.getElevesPrevus(con, date);

        /*System.out.println("    NBR el: "+source.getBatiments().get(0).getElMatinee());
        //source.setDemandeMoy(source.getDemandeMoy(meteoPrec));
        source.setDemandeMoy(42);
        Etat e = null;
        try{
            e = source.getEtat(8, meteo.getLuminosite().get(8));
            System.out.println(e.toString());
            e = source.getEtat(9, meteo.getLuminosite().get(9));
            System.out.println(e.toString());
            e = source.getEtat(10, meteo.getLuminosite().get(10));
            System.out.println(e.toString());
            e = source.getEtat(11, meteo.getLuminosite().get(11));
            System.out.println(e.toString());
            e = source.getEtat(12, meteo.getLuminosite().get(12));
            System.out.println(e.toString());

        }
        catch(BatterieInsuffisanteException ins){
            e=ins.getEtat();
            System.out.println(e.toString());

        }*/


        //System.out.println("!!!!!!!!!!!!!!! TACHE AUTOMATIQUE !!!!!!!!!");
        //source.getBatterie().setReserve(source.getBatterie().getCapacite());        
        //Vector<Etat> etats = source.getListeEtat(8, 17, meteo);
        //System.out.println("#### DEMANDE MOY: "+source.getDemandeMoy());
        //for(Etat eX: etats){
        //        System.out.println(eX.toString());
        //    }

        //source.getElevesPrevus(con, date);
        //System.out.println("AM: "+source.getBatiments().get(0).getElAm());
        //System.out.println("MAT: "+source.getBatiments().get(0).getElMatinee());


        /*for(Source source: sources){
            double demande = source.getDemandeMoy(meteoPrec);
    
            source.setDemandeMoy(demande);
            source.getElevesPrevus(con, date);
            //source.resetBatterie();
            source.setListeEtat(source.getListeEtat(8, 17, meteo));
            
        }

        for(Source source: sources){
            System.out.println("Source: "+source.getNomSource());
            System.out.println("Demande: "+ source.getDemandeMoy());
              for(Etat e: source.getListeEtat()){
                System.out.println(e.toString());
            }
        }*/


        //Source s1 = sources.get(0);
        //for(Batiment b: s1.getBatiments()){
        //    b.setElMatinee(273);
        //    b.setElAm(200);
        //}
        //s1.setDemandeMoy(38);
        //Etat e;
        //Vector<Etat> etats = null; 
        //double demande = 0 ;
        //try{
        //    etats = s1.getListeEtat(8, 17, meteo);
//
        //    for(Etat etat: etats){
        //    System.out.println("STRINGIFYING");
        //    System.out.println(etat.toString());
        //    
        //    }
        //    
        //    Date daty = Date.valueOf("2023-12-7");
        //    demande = s1.getDemandeMoy(daty, meteoPrec.get(daty));
        //}
        //catch(BatterieInsuffisanteException btex){
        //     btex.printStackTrace();
        //}
        //catch(Exception ex){
           // ex.printStackTrace();
        //}
         
        
        //System.out.println("DEMANDE MOY: "+ demande);
        //.out.println(e.toString());

        //Vector<Etat> etats = sources.get(0).getListeEtat();
        //Etat etat = etats.get(etats.size()-1);
        //System.out.println("heure de coupure: "+etat.getHeure());

    }
}
