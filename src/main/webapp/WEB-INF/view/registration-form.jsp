<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>

<head>
    <style>
        .error {
            color: red
        }
    </style>

    <title>
        Plain Registration Form
    </title>
</head>

<body>

<h3>Registration form</h3>
<hr>

<form:form action="${pageContext.request.contextPath}/register/processRegistration" method="POST"
           modelAttribute="regUser">
    <p><i>First name(*): <form:input path="firstName"/></i>
        <i class="error"><form:errors path="firstName"/></i></p>

    <p><i>Last name(*): <form:input path="lastName"/></i>
        <i class="error"><form:errors path="lastName"/></i></p>

    <p><i>User name(*): <form:input path="userName"/></i>
        <i class="error"><form:errors path="userName"/></i></p>

    <p><i>Email(*): <form:input path="email"/></i>
        <i class="error"><form:errors path="email"/></i></p>

    <p><i>Date of birth(*): <form:input type="date" path="dateOfBirth"/></i>
        <i class="error"><form:errors path="dateOfBirth"/></i></p>

    <p><i>Password(*): <form:password path="password" showPassword="true"/></i>
        <i class="error"><form:errors path="password"/></i></p>

    <p><i>Confirm password(*): <form:password path="matchingPassword" showPassword="true"/></i>
        <i class="error"><form:errors path="matchingPassword"/></i></p>

    <input type="submit" value="Submit"/>
</form:form>
<br>
<a href="${pageContext.request.contextPath}/showLoginPage">Back to Login Page</a>

</body>

</html>