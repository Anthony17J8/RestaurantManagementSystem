<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>

<head>
    <title>
        Add Restaurant
    </title>
</head>

<body>

<h3>Add Restaurant</h3>
<hr>


<form:form action="addRestaurant" method="post" modelAttribute="restaurant">
<form:hidden path="id"/>
<table>
    <tbody>
    <tr>
        <td>Name:</td>
        <td><form:input path="name"/></td>
    </tr>
    <form:form action="addRestaurant" method="post" modelAttribute="detail">
        <form:hidden path="detailId"/>
        <tr>
            <td>Country:</td>
            <td><form:input path="country"/></td>
        </tr>

        <tr>
            <td>City:</td>
            <td><form:input path="city"/></td>
        </tr>

        <tr>
            <td>Street:</td>
            <td><form:input path="street"/></td>
        </tr>

        <tr>
            <td>Phone:</td>
            <td><form:input path="phone"/></td>
        </tr>

        <td>Email:</td>
        <td><form:input path="email"/></td>

    </form:form>
    <tr>
        <td><label></label></td>
        <td><input type="submit" value="Save" class="save"/></td>
    </tr>
    </form:form>
    </tbody>
</table>


</body>
</html>