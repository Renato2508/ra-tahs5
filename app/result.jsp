<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "solaire.*" %>
<%@ page import="java.text.DecimalFormat" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        body {
   font-family: Arial, sans-serif;
   margin: 0;
   padding: 0;
   background-color: #f0f0f0;
}

h1 {
   text-align: center;
   padding: 20px 0;
   color: #333;
}

h2 {
   text-align: left;
   padding: 20px;
   color: #333;
}

table {
   width: 100%;
   border-collapse: collapse;
   margin: 20px 0;
}

th, td {
   border: 1px solid #ddd;
   padding: 10px;
   text-align: left;
}

th {
   background-color: #4CAF50;
   color: white;
}

tr:nth-child(even) {
   background-color: #f2f2f2;
}

    </style>
    <title>Prévisions de coupure</title>
</head>
<body>
    <h1>PREVISIONS DE COUPURE PAR SOURCE</h1>

    <%
        Vector<Source> sources = (Vector<Source>) request.getAttribute("sources");   
        Vector<Etat> etats = null;         
        for (Source s: sources) {
    %> 
        <h2><%  out.print(s.getNomSource() + String.format("(conso moy estimée: %f W)", s.getDemandeMoy())); %></h2>
        <%  etats = s.getListeEtat();  
        %>
        <table  border ="1">
            <thead>
                <tr>
                    <th>Heure Début</th>
                    <th>Heure Fin</th>
                    <th>Elèves Branchés </th>
                    <th>Demande(W)</th>
                    <th>Demande Panneau(W)</th>
                    <th>Demande Batterie(W)</th>
                    <th>Batterie Initiale(Wh) (%)</th>
                    <th>Reserve Batterie (Wh) (%)</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Etat etat : etats) {
                %>
                <tr>    
                    <%  DecimalFormat df = new DecimalFormat();  %>

                    <td><%= etat.getHeure() %></td>
                    <td><%= etat.getHeureFin() %></td>
                    <td><%= etat.getNbrel() %></td>
                    <td><%= df.format(etat.getDemande()) %></td>
                    <td><%= df.format(etat.getDemandePanneau()) %></td>
                    <td><%= df.format(etat.getDemandeBatterie()) %></td>
                    <td><%= df.format(etat.getBatterieInitiale()) + " (" + df.format(etat.getBatterieInitialePerc()) + " %)" %></td>
                    <td><%= df.format(etat.getReserveBatterie()) + " (" + df.format(etat.getReserveBatteriePerc()) + " %)" %></td>
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
