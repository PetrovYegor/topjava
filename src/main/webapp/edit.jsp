<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<form method="POST" action='meals?action=edit&mealId=<c:out value="${param.mealId}"/>'>

    DateTime : <input type="datetime-local" name="mealDate"
                      value="<c:out value="${param.mealDate.trim().format(DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm\"))}"/>"/>
    <br/>
    Description : <input type="text" name="description" value="<c:out value="${param.description}"/>"/>
    <br/>
    Calories : <input type="text" name="calories" value="<c:out value="${param.calories}"/>"/>
    <br/>
    <input type="submit" value="Save"/>
    <input type="button" value="Cancel" onclick="window.location='meals'">
</form>
</body>
</html>