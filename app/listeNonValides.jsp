<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "mouvement.Mouvement" %>

<!DOCTYPE html>
<html>
<head>
    <title>Validation de sorties</title>
</head>
<body>
    <h1>Validation de sorties</h1>

    <table border="1">
        <th>Date de Saisie</th>
        <th>idArticle</th>
        <th>idMagasin</th>
        <th>Quantité</th>

        <%
            List<Mouvement> mouvs = (List<Mouvement>) request.getAttribute("mouvementsNonValides");
            
            for (Mouvement mouv: mouvs) {
        %>
            <tr>
                 <td> <% out.print(mouv.getDate()); %> </td>
                 <td> <% out.print(mouv.getArticle().getIdArticle()); %> </td>
                 <td> <% out.print(mouv.getMagasin().getIdMagasin()); %> </td>
                 <td> <% out.print(mouv.getQuantite()); %> </td>
                 <td> <a href="ajoutmouvement?date=<%= mouv.getDate() %>&idarticle=<%= mouv.getArticle().getIdArticle() %>&idmagasin=<%= mouv.getMagasin().getIdMagasin() %>&quantite=<%= mouv.getQuantite() %>">VALIDER</a></td> 
             </tr>

        <%
            }
        %>

    </table>

    
    

</body>
</html>
