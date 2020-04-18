<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnc" uri="http://icoltd.rvs.ru/functions" %>
<html>

<head>
    <title>Menus</title>
</head>

<body>

<h3><c:out value="${restaurant.name}"/> <br></h3>
<h4>Menus</h4>
<hr>

<sec:authorize access="hasRole('ADMIN')">

    <c:url value="/restaurant/${restaurant.id}/menu/showFormForAdd" var="formForAdd"/>
    <a href="${fn:escapeXml(formForAdd)}">Add menu</a>

</sec:authorize>

<table border="1" cellpadding="20">
    <thead>
    <tr>
        <th>Name</th>
        <th>Date</th>
        <th>Votes</th>

        <sec:authorize access="hasRole('ADMIN')">
            <th>Action</th>
        </sec:authorize>
    </tr>
    </thead>
    <c:forEach var="menu" items="${menus}">
        <tbody>
        <tr>
            <c:url value="/restaurant/${restaurant.id}/menu/${menu.id}/dish/showAll" var="menuDetailsLink"/>
            <td><a href="${fn:escapeXml(menuDetailsLink)}"><c:out value="${menu.name}"/></a></td>
            <td><c:out value="${menu.date}"/></td>
                <%--todo through DTO object--%>
            <td><c:out value="1"/></td>

            <sec:authorize access="hasRole('ADMIN')">
                <c:url var="deleteLink" value="/restaurant/${restaurant.id}/menu/${menu.id}/delete"/>

                <c:url var="updateLink" value="/restaurant/${restaurant.id}/menu/${menu.id}/update"/>

                <td>
                    <a href="${fn:escapeXml(updateLink)}">Update</a>
                    |
                    <a href="${fn:escapeXml(deleteLink)}">Delete</a>
                </td>
            </sec:authorize>
        </tr>
        </tbody>
    </c:forEach>

</table>
</body>

<jsp:include page="footer.jsp"/>
</html>
