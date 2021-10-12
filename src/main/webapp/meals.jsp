<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="mealWE" items="${allMealsWithExcess}">
        <tr style="background-color:${mealWE.excess ? 'red' : 'greenyellow'}">
            <td>${mealWE.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}
            <td>${mealWE.description}</td>
            <td>${mealWE.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>