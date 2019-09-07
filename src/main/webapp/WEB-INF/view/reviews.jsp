<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnc" uri="http://icoltd.rvs.ru/functions" %>
<html>

<head>
    <title>Reviews</title>
</head>

<body>

<h3>Reviews</h3>

<hr>

<table border="1" cellpadding="20">
    <thead>
    <tr>
        <th>Username</th>
        <th>Date of review</th>
        <th>Text</th>
    </tr>
    <c:forEach items="${reviews}" var="review">
        <tr>
            <th>${review.user.userName}</th>
            <th>${fnc:formatLocalDateTime(review.createdAt)}</th>
            <th>${review.text}</th>
        </tr>
    </c:forEach>
    </thead>

</table>
<br/>
<a href="${fn:escapeXml(pageContext.request.contextPath)}/restaurant/list">View all restaurants</a>
</body>

<jsp:include page="footer.jsp"/>
</html>
