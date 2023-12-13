<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "solaire.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Prévisions de coupure</title>
</head>
<body>
    <h1>PREVISIONS DE COUPURE PAR SOURCE</h1>

    <%
        Vector<Source> sources = (Vector<Source>) request.getAttribute("sources");   
        Vector<Etat> etats = null;         
        for (Source s: sources) {
    %>
        <h2><%  out.print(s.getNomSource()); %></h2>
        <%  etats = s.getListeEtat();  
        %>
        <table  border ="1">
            <thead>
                <tr>
                    <th>Heure</th>
                    <th>Heure Fin</th>
                    <th>Demande</th>
                    <th>Demande Panneau</th>
                    <th>Demande Batterie</th>
                    <th>Batterie Initiale (%)</th>
                    <th>Reserve Batterie (%)</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Etat etat : etats) {
                %>
                <tr>
                    <td><%= etat.getHeure() %></td>
                    <td><%= etat.getHeureFin() %></td>
                    <td><%= etat.getDemande() %></td>
                    <td><%= etat.getDemandePanneau() %></td>
                    <td><%= etat.getDemandeBatterie() %></td>
                    <td><%= etat.getBatterieInitiale() + " (" + etat.getBatterieInitialePerc() + " %)" %></td>
                    <td><%= etat.getReserveBatterie() + " (" + etat.getReserveBatteriePerc() + " %)" %></td>
                    <td><%= etat.getStatus() %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    
        <%
            }
        %>

    

</body>
</html>
