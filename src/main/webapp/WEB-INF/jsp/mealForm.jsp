<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<section>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <%--    <form method="post" action="meals">--%>
    <c:url var="updateButton" value="http://localhost:8080/topjava/meals/save">
        <c:param name="id" value="${meal.id}"/>
        <c:param name="dateTime" value="${meal.dateTime}"/>
        <c:param name="description" value="${meal.description}"/>
        <c:param name="calories" value="${meal.calories}"/>
    </c:url>
    <input type="hidden" name="id" value="${meal.id}">
    <dl>
        <dt><spring:message code="meal.time"/></dt>
        <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
    </dl>
    <dl>
        <dt><spring:message code="meal.description"/></dt>
        <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>
    </dl>
    <dl>
        <dt><spring:message code="meal.calories"/></dt>
        <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
    </dl>
    <input type="button" value=
    <spring:message code="meal_edit.save"/>
            onclick="window.location.href='${updateButton}'"/>
    <button onclick="window.history.back()" type="button"><spring:message code="meal_edit.cancel"/></button>
    <%--    </form>--%>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>