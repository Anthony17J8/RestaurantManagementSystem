<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="wj" uri="http://www.webjars.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Page</title>
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/hover.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/style.css">
</head>
<body id="background-form">

<div class="container">
    <form:form action="${pageContext.request.contextPath}/register/processRegistration" method="POST"
               modelAttribute="regUser">

    <div class="row py-4">
        <div class="shadow-form col-6 offset-3 px-0">

            <div id="title" class="row m-0 px-0 py-3">
                <h1 class="text-center m-0">Sign up</h1>
            </div>

            <div id="form-content" class="px-5 py-3">
                <div class="form-row">
                    <div class="form-group col-6">
                        <label for="fName">First Name</label>
                        <form:input path="firstName" cssClass="form-control" id="fName"/>
                        <form:errors path="firstName"/>
                    </div>

                    <div class="form-group col-6">
                        <label for="lName">Last Name</label>
                        <form:input path="lastName" cssClass="form-control" id="lName"/>
                        <form:errors path="lastName"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="uName">User name</label>
                    <form:input path="userName" id="uName" cssClass="form-control"/>
                    <form:errors path="userName"/>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <form:input path="email" id="email" cssClass="form-control"/>
                    <form:errors path="email"/>
                </div>

                <div class="form-group">
                    <label for="bDate">Date of birth</label>
                    <form:input type="date" path="dateOfBirth" id="bDate" cssClass="form-control"/>
                    <form:errors path="dateOfBirth"/>
                </div>

                <div class="form-group">
                    <label for="pass"> Password</label>
                    <form:password path="password" showPassword="true" id="pass" cssClass="form-control"/>
                    <form:errors path="password"/>
                </div>

                <div class="form-group">
                    <label for="cPass">Confirm password</label>
                    <form:password path="matchingPassword" showPassword="true" id="cPass" cssClass="form-control"/>
                    <form:errors path="matchingPassword"/>
                </div>

                <div class="form-group">
                    <c:forEach var="role" items="${roleNames}">
                        <div class="form-check form-check-inline custom-checkbox">
                            <form:checkbox id="${role}" path="roles" value="${role}"
                                           cssClass="form-check-input custom-control-input"/>
                            <label class="form-check-label custom-control-label" for="${role}">${role}</label>
                        </div>
                    </c:forEach>
                </div>

                <div class="row">
                    <div class="col">
                        <button class="btn btn-block btn-prm" type="submit">Submit</button>
                    </div>
                    <div class="col">
                        <button id="btn-cancel" class="btn btn-block" onclick="window.history.back()">Cancel</button>
                    </div>
                </div>

            </div>
        </div>
        </form:form>
    </div>
        <script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
        <script type='text/javascript' src='<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/form.js"></script>
</body>

</html>