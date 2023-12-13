package servlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import solaire.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Vector;

import connect.Connect;

@WebServlet("/prevision")
public class ServletPrevision  extends HttpServlet{
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String daty = request.getParameter("date");
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
                 request.setAttribute("sources", sources);
                 RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
                 rd.forward(request, response);

                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
