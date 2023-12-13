<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "etat_stock.produit.Article" %>
<%@ page import = "etat_stock.storage.Magasin" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire de Mouvement</title>
    <style>
        .erreur{
            color: red;
        }
    </style>
</head>
<body>
    <h1>Formulaire de Mouvement</h1>
    <%
        String erreur = (String)request.getParameter("error");
        if(erreur != null){
            if(erreur != ""){
    %>
        <p class="erreur"><% out.print(erreur); %></p>
    <%
            }
        }
    %>
    <form action="ajoutsortiebrouillon" method="post">
        <label for="date">Date :</label>
        <input type="date" name="date" id="date" required><br>

        <label for="idarticle">Article :</label>
        <select name="idarticle" required>
            <%
                List<Article> articles = (List<Article>) request.getAttribute("articles");
                for (Article article : articles) {
            %>
                <option value="<%= article.getIdArticle() %>"><%= article.getNomArticle() %></option>
            <%
                }
            %>
        </select><br>

        <label for="quantite">Quantité :</label>
        <input type="text" name="quantite" id="quantite" required><br>

        <label for="idmagasin">Magasin :</label>
        <select name="idmagasin" required>
            <%
                List<Magasin> magasins = (List<Magasin>) request.getAttribute("magasins");
                for (Magasin magasin : magasins) {
            %>
                <option value="<%= magasin.getIdMagasin() %>"><%= magasin.getNomMagasin() %></option>
            <%
                }
            %>
        </select><br>

        <input type="submit" value="Valider">
    </form>
    <p><a href = "getMouvementsNonValides">VALIDER DES SORTIES</a></p>

</body>
</html>
