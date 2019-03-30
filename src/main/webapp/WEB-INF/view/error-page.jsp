<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Confirmation page</title>
</head>

<body>

<h3>You can't vote for menu with later date</h3>

<a href="${pageContext.request.contextPath}/restaurant/${restId}/menus">Back to Menu details</a>

</body>
</html>