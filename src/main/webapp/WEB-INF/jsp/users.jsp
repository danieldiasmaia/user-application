<%--
Created by IntelliJ IDEA.
User: codecadet
Date: 15/07/16
Time: 11:49
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="org.academiadecodigo.bootcamp.controller.Attribute" %>

<html>

    <head>
        <title>Main Page</title>

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head;
         any other head content must come *after* these tags -->

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
              integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
              crossorigin="anonymous">

        <!-- Optional bootstrap theme -->
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
              integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
              crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
                integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
                crossorigin="anonymous"></script>
    </head>

    <body>
        <h2>Welcome to our application ${loginUser.name}!
            <form:form action="logout" method="get" modelAttribute="users"><button type="submit" class="btn btn-primary">Logout</button></form:form>
        </h2>


        <form:form action="user/add" class="form-inline" method="post" modelAttribute="user">
            <div class="form-group">
                <label for="name">Name</label>
                <form:input path="name" type="text" class="form-control" />
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <form:input path="password" type="password" class="form-control" />
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <form:input path="email" class="form-control" />
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-warning">Reset</button>
        </form:form>  

        <table class="table">
        <c:if test="${not empty users}">
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td><form:form action="user/edit/${user.name}" method="get" modelAttribute="user"><button type="submit" class="btn btn-success">Edit</button></form:form></td>
                    <td><form:form action="user/delete/${user.name}" method="get" modelAttribute="user"><button type="submit" class="btn btn-danger">Delete</button></form:form></td>
                </tr>
            </c:forEach>

        </c:if>
        </table>

    </body>

</html>
