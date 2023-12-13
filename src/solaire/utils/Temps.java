package solaire.utils;

import java.sql.Time;

public class Temps {
    public static Time getTime(double nbrSecondes){
        Utils.getMethodInfo();

        int nbrHeures = (int)nbrSecondes / 3600;
        nbrSecondes %= 3600;
        int nbrMinutes = (int)nbrSecondes / 60;
        nbrSecondes %= 60;
    
        return new Time(nbrHeures, nbrMinutes, (int)nbrSecondes);
    }
}
