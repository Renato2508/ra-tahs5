<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prévisions de coupure</title>
</head>
<body>
    <h1>Choisir une date pour générer la prévision</h1>
    <form action="prevision" method="get">
        <label for="dt1">Date de prévision :</label>
        <input type="date" id="dt1" name="date"><br>

         <label for="dt1">Precision des previsions (secondes):</label>
        <input type="text" id="dt1" name="precision"><br>


        <input type="submit" value="Générer les prévisions">
    </form>

</body>
</html>
