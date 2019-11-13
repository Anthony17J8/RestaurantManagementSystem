<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fnc" uri="http://icoltd.rvs.ru/functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <title>Menus</title>
</head>

<body>

<h3>Top Menu List</h3>
<hr>

<form:form method="get" action="${pageContext.request.contextPath}/menu/toplist">
    <dl>
        <dd><input type="date" name="startDate" value="${fn:escapeXml(param.startDate)}"/></dd>
    </dl>
    <dl>
        <dd><input type="date" name="endDate" value="${fn:escapeXml(param.endDate)}"/></dd>
    </dl>
    <button type="submit">Search</button>
</form:form>
<br/>

<table border="1" cellpadding="20">
    <thead>
    <tr>
        <th>Restaurant</th>
        <th>Menu</th>
        <th>Date</th>
        <th>Votes</th>
    </tr>
    </thead>
    <c:forEach var="menu" items="${menus}">

        <c:url value="/menu/showDetails" var="viewMenu">
            <c:param name="menuId" value="${menu.id}"/>
        </c:url>

        <tbody>
        <tr>
            <td><c:out value="${menu.restaurant.name}"/></td>
            <td><a href="${fn:escapeXml(viewMenu)}"><c:out value="${menu.name}"/></a></td>
            <td><c:out value="${fnc:formatLocalDateTime(menu.date)}"/></td>
            <td><c:out value="${menu.votes.size()}"/></td>

        </tr>
        </tbody>
    </c:forEach>
</table>

<br/>
<a href="${fn:escapeXml(pageContext.request.contextPath)}/restaurant/list">Restaurant List</a>
<br/><br/>

<form:form method="post" action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="Logout">
</form:form>

</body>

</html>
