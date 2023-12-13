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

            Connect connect = new Connect();
            connect.getConnectionPostGresql();
            Connection con = connect.getConnection();
            
            //chargement des données meteo
            HashMap<Date, Meteo> meteoPrec = Meteo.getMap(Meteo.getAll(con));
            
            // chargement des données des sources
            Vector<Source> sources = Source.getAll(con);
            

            
            request.setAttribute(nomBouquet, nomBouquet);
            response.sendRedirect("Post_Bouquet.html");
    }
}
