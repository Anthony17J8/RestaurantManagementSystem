<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>

<head>
    <title>
        Confirmation Page
    </title>
</head>

<body>

<h3>You have been registered successful</h3>

<a href="${pageContext.request.contextPath}/showLoginPage">Go to Login Page</a>
</body>

</html>