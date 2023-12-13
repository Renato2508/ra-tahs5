package solaire;

import java.util.HashMap;
import java.util.Vector;

import solaire.exception.BatterieInsuffisanteException;
import solaire.exception.PrecisionException;
import solaire.utils.Dichotomie;
import solaire.utils.Utils;

import java.util.Set;

import java.sql.Date;
import java.sql.Time;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Source {
    int idSource;
    String nomSource;
    Panneau panneau;
    Batterie batterie;
    double demandeMoy;
    int nbrEleves;
    Vector<Etat> listeEtat;
    Vector<Batiment> batiments = new Vector<Batiment>();
    HashMap<Date, Time> coupuresPrec = new HashMap<Date, Time>();




    public Source(int idSource, String nomSource, Panneau panneau, Batterie batterie) {
        this.idSource = idSource;
        this.nomSource = nomSource;
        this.panneau = panneau;
        this.batterie = batterie;
    }



    public Source() {
    }

    public void resetBatterie() throws Exception{
        this.batterie.setReserve(this.batterie.getCapacite());
    }  

    public void getElevesPrevus(Connection conn, Date date) throws Exception{
        Utils.getMethodInfo();
        System.out.println("----> Source: "+ this.nomSource+ " Recherche du nombre d'élèves prévus");


        for(Batiment batiment: this.batiments){
            batiment.setElMatinee(batiment.getElevesPrevus(conn, date, "M"));
            batiment.setElAm(batiment.getElevesPrevus(conn, date, "A"));
        }
    }


    public static Vector<Source> getAll(Connection conn)throws Exception{
        Utils.getMethodInfo();
        Vector<Source> res = new Vector<Source>();
        try {
            String sql = "SELECT * FROM v_source_all order by idsource asc, idbatiment asc";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Source source = new Source();
            source.setIdSource(-1);
            Batiment batiment = new Batiment();
            batiment.setIdBatiment(-1);

            while (rs.next()) {
                if(rs.getInt("idsource") != source.getIdSource()){

                    source = new Source(rs.getInt("idsource"),rs.getString("nomsource"), new Panneau(rs.getInt("idpanneau"), rs.getString("nompanneau"), rs.getDouble("puissance")), new Batterie(rs.getInt("idbatterie"), rs.getString("nombatterie"), rs.getDouble("capacite")));

                    res.add(source);

                }
                source.getCoupuresPrec().put(rs.getDate("datecoupure"),rs.getTime("heurecoupure"));

                if(rs.getInt("idbatiment") != batiment.getIdBatiment()){
                    batiment = new Batiment(rs.getInt("idbatiment"), rs.getString("nombatiment"));
                    
                    source.getBatiments().add(batiment);
                }

                if(rs.getString("tranchehoraire").equals("M")){
                    batiment.getElMatineePrec().put(rs.getDate("datepresence"), rs.getInt("nbreleve"));
                }
                else if(rs.getString("tranchehoraire").equals("A")){
                    batiment.getElAMPrec().put(rs.getDate("datepresence"), rs.getInt("nbreleve"));
                }


            }

            return res;
            
        } catch (Exception e) {
            throw e  ;      
        }
        
    }


    public double getDemandeMoy(Date date, Meteo meteo) throws Exception{
        Utils.getMethodInfo();
        System.out.println(this.nomSource+"----> Calcul de la demande moyenne le "+ date);

        // parcours de la liste de batiments pour récupérer le nombre d'etudiants
        // présents ce jour là
        for(Batiment bat: this.batiments){
            bat.setElMatinee(bat.getElMatineePrec().get(date));
            bat.setElAm(bat.getElAMPrec().get(date));
            System.out.println("#### PRESENTS MATIN: "+bat.getElMatinee());
            System.out.println("#### PRESENTS AM: "+bat.getElAm());
        }

        // heure de coupur ce jour là
        Time heureCoupure = this.coupuresPrec.get(date);
        System.out.println("H#### Heure de coupure cible: "+heureCoupure);
        // on commence par une valeur arbitraire de la puissance par etudiant
        this.setDemandeMoy(50);
        
        try {
            // premiere estimtion avec la valeur par défaut
            Vector<Etat> etats = this.getListeEtat(8, 17, meteo);
            Etat etat = etats.get(etats.size()-1);
            Time heureCoupureEstimee = etat.getHeureFin();
            System.out.println("#### ESTIMATIONS SUR LA COUPURE: "+etat.toString());
            if(heureCoupureEstimee.equals(heureCoupure))
                return this.demandeMoy;
            
            // estimation et heure de coupure réelle divergentes
            else{
                //System.out.println("");
                Dichotomie dichotomie = new Dichotomie(this.demandeMoy);

                while(heureCoupureEstimee.equals(heureCoupure) == false){
                    System.out.println(String.format("#### REELLE: %s   ESTIMATION: %s",heureCoupure, heureCoupureEstimee));

                    // remettre la réserve à sa capacité initiale à chaque itération de la simulation 
                    //this.batterie.setReserve(this.batterie.getCapacite());

                    // estimation < reelle
                    if(heureCoupureEstimee.before(heureCoupure)){
                        System.out.println("#### COUPURE TROP TOT");
                        this.setDemandeMoy(dichotomie.dichotomie('-'));
                        etats = this.getListeEtat(8, 17, meteo);
                        etat = etats.get(etats.size()-1);
                        heureCoupureEstimee = etat.getHeureFin();
                        System.out.println("#### ESTIMATIONS SUR LA COUPURE: "+etat.toString());

                    }

                    //estimation > reelle
                    else if(heureCoupureEstimee.after(heureCoupure)){
                        System.out.println("#### COUPURE TROP TARD");

                        this.setDemandeMoy(dichotomie.dichotomie('+'));
                        etats = this.getListeEtat(8, 17, meteo);
                        etat = etats.get(etats.size()-1);
                        heureCoupureEstimee = etat.getHeureFin();
                        System.out.println("#### ESTIMATIONS SUR LA COUPURE: "+etat.toString());


                    }
                }
                return this.demandeMoy;
            }
            
         } 
        catch(PrecisionException pe) {
            return this.demandeMoy;
        } catch (Exception e) {
            throw e;
        }
        
    }

    public double getDemandeMoy(HashMap<Date, Meteo> meteo) throws Exception{
        Utils.getMethodInfo();
        System.out.println(this.nomSource+"----> Calcul de la demande moyenne journalière");
        Set<Date> keys = meteo.keySet();
        Meteo meteoCourante = null;
        double demandeJ;
        Vector<Double> liste = new Vector<Double>();

        try {

            for(Date date: keys){
            meteoCourante = meteo.get(date);
            demandeJ = this.getDemandeMoy(date, meteoCourante);
            liste.add(demandeJ);
            }

            // calcul de la moyenne
            Double somme = .0;
            for(Double dem: liste){
                somme = dem + somme;
            }
            return somme / liste.size();
        } catch (Exception e) {

            throw  e;      
        }
        
    }

    public Vector<Etat> getListeEtat(int heureDebut, int heureFin,Meteo meteo) throws Exception{
        Utils.getMethodInfo();
        System.out.println(this.nomSource+"------> calcul des états entre "+heureDebut +" et "+heureFin);
        Vector<Etat> res = new Vector<Etat>();

        this.resetBatterie();
        Integer luminosite;
        for(int heure = heureDebut; heure < heureFin; heure++){
            System.out.println(">>>>> Calcul de l'etat a: "+heure + "h");
            luminosite = meteo.getLuminosite().get(heure);
            if(luminosite==null)
                luminosite = 0;

            Etat etat = null;
            try {
                etat = this.getEtat(heure, luminosite);
                res.add(etat);
            } catch (BatterieInsuffisanteException e) {
                System.out.println("!!!!! Coupure détectée: "+this.nomSource + " HEURE: " + e.getEtat().getHeureFin());
                res.add(e.getEtat());
                break;
            }
            catch (Exception ex){
                //ex.printStackTrace();
                throw ex;
            }
        }
        return res;
    }

    public Etat getEtat(int heureDebut, int lumiere) throws Exception, BatterieInsuffisanteException{
        Utils.getMethodInfo();
        double reserveInitiale = this.batterie.getReserve();
        double reserveInitialePerc = this.batterie.getReservePerc();
        Time heureInit = new Time(heureDebut, 0, 0);

        System.out.println("####    source: "+this.nomSource+ " calcul de l'état à: "+ heureDebut +" h" );
        //System.out.println("");
        Etat res = null;
        String  msg;

        // demande pontuelle
        double demande = 0.0;
        int nbEleves = 0;

        for(Batiment b: this.batiments){
            if(heureDebut < 12)
                nbEleves += b.getElMatinee();
            else nbEleves += b.getElAm();
        }
        demande = nbEleves * this.demandeMoy;
        this.setNbrEleves(nbEleves);

        // puissance solaire disponible
        double solaireDispo = this.panneau.getPuissance()*((double)lumiere/10);
        //System.out.println("PUISSANCE: " + /*this.panneau.getPuissance()/**/(lumiere/10));
        //msg =String.format(" ##### Demande ponctuelle à %d h :  %f W        Capacite SOlaire Max: %f W       //solaire Disponible:  %f W       Batterrie: %f  Wh       Niveau de batterie: %f  \n  ",heureDebut, demande, this.//panneau.getPuissance(), solaireDispo, this.batterie.getCapacite(), this.batterie.getEnergieDispo());
        //System.out.println(msg);

        // cas d'usage de la batterie;
        if(solaireDispo < demande){
            System.out.println("BRANCHEMENT SUR BATTERIE");
            // calcul de la durée restante de la batterie
            double demandeBatterie = demande - solaireDispo;
            double dureeRestante = this.batterie.getTempsRestant(demandeBatterie);

            // ici la batterie ne tiendra pas une heure 
            if(dureeRestante < 1){
                    System.out.println("Batterie ne suffira pas");
                    // convertir la virgule en notation sexa
                    double nbrSecondes = (heureDebut+dureeRestante)*3600;
                    Time heureCoupure = solaire.utils.Temps.getTime (nbrSecondes);

                     // diminuer la réserve d'energie
                     try {

                     this.batterie.setReserve(this.batterie.getCapacite()   * Batterie.getInutilisable());
                     } catch (Exception ex) {
                        throw ex;
                    }
                     double batterieRestePerc = this.batterie.getReservePerc();

                     // l'etat final
                     //Etat etat = new Etat(this,heureCoupure, demande,   solaireDispo, demande-solaireDispo, this.batterie.getReserve(), batterieRestePerc, "DOWN");

                     Etat etat=new Etat(this,heureInit,heureCoupure,demande, solaireDispo, demandeBatterie, reserveInitiale, reserveInitialePerc, this.batterie.getReserve(), batterieRestePerc,"DOWN");

                    // constructeur de l'Exception
                    BatterieInsuffisanteException e = new  BatterieInsuffisanteException("Batterie épuisée à "   +heureCoupure, etat);
                    throw e;

                    
                
                
            // la batterie a suffi pour tenir 1 heure
            }else{
                System.out.println("Batterie encore en etat apres "+ heureDebut+ "h");
                try{
                    double energieDispo = this.batterie.getEnergieDispo();
                    double capacite = this.batterie.getCapacite();
                    double inutilisable = Batterie.getInutilisable();
                    this.batterie.setReserve(energieDispo - demandeBatterie + capacite * inutilisable);

                    double reservePerc = this.batterie.getReservePerc();
                    Time heureFin = new Time(heureDebut+1,0 ,0);
                
                    //res = new Etat(this, heureInit, heurFin, demande, solaireDispo, demande-solaireDispo, this.batterie.getReserve(), reservePerc, "UP"); 


                     res =new Etat(this,heureInit, heureFin, demande, solaireDispo, demande-solaireDispo, reserveInitiale, reserveInitialePerc, this.batterie.getReserve(), reservePerc,"UP");
                }
                catch(Exception e){
                        throw e;                
                }

                           
            }
        }
        // le solaire seul suffit  pour alimenter tout
        else{
            System.out.println("Le solaire a suffi pour la tranche : "+ heureDebut +" h");
            Time heure = new Time(0,0,0);
            Time heureFin = new Time(heureDebut+1,0 ,0);
            //res = new Etat(this,new Time(heureDebut,0,0), demande, solaireDispo, 0, this.batterie.getReserve(), this.batterie.getReservePerc(), "UP");

            res =new Etat(this,heureInit, heureFin, demande, solaireDispo, 0, reserveInitiale, reserveInitialePerc, this.batterie.getReserve(), this.batterie.getReservePerc(),"UP");
        }
        //System.out.println(res.toString());
        return res;
    }

    

    public int getIdSource() {
        return idSource;
    }
    public void setIdSource(int idSource) {
        this.idSource = idSource;
    }
    public Panneau getPanneau() {
        return panneau;
    }
    public void setPanneau(Panneau panneau) {
        this.panneau = panneau;
    }
    public Batterie getBatterie() {
        return batterie;
    }
    public void setBatterie(Batterie batterie) {
        this.batterie = batterie;
    }
    public double getDemandeMoy() {
        return demandeMoy;
    }
    public void setDemandeMoy(double demandeMoy) {
        this.demandeMoy = demandeMoy;
    }
    public Vector<Etat> getListeEtat() {
        return listeEtat;
    }
    public void setListeEtat(Vector<Etat> listeEtat) {
        this.listeEtat = listeEtat;
    }
    public Vector<Batiment> getBatiments() {
        return batiments;
    }
    public void setBatiments(Vector<Batiment> batiments) {
        this.batiments = batiments;
    }
    public HashMap<Date, Time> getCoupuresPrec() {
        return coupuresPrec;
    }
    public void setCoupuresPrec(HashMap<Date, Time> coupuresPrec) {
        this.coupuresPrec = coupuresPrec;
    }



    public String getNomSource() {
        return nomSource;
    }



    public void setNomSource(String nomSource) {
        this.nomSource = nomSource;
    }



    public int getNbrEleves() {
        return nbrEleves;
    }



    public void setNbrEleves(int nbrEleves) {
        this.nbrEleves = nbrEleves;
    }
    
}
