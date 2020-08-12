<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"/>
    <link href="<c:url value='/static/css/shared.css' />" rel="stylesheet"/>
</head>
<body>
<div class="container-sm">
    <br/>
    <blockquote class="blockquote">
        <button class="btn btn-dark ml-auto mb-0">
            <a class="text-white" href="<c:url value='/user/add' />">Create new user</a>
        </button>
    </blockquote>
    <c:choose>
        <c:when test="${fn:length(userResponseList) gt 0}">
            <h4> Users </h4>
            <table border="1" class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th scope="col" colspan="2">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userResponseList}" var="user" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.index+1}</th>
                        <td>${user.firstName}</td>
                        <c:choose>
                            <c:when test="${user.gender.equalsIgnoreCase('Male')}">
                                <td style="color:#33ccff">${user.gender}</td>
                            </c:when>
                            <c:otherwise>
                                <td style="color:#ff3385">${user.gender}</td>
                            </c:otherwise>
                        </c:choose>
                        <td><a class="btn btn-secondary" href="<c:url value="/user/edit/${user.id}" />">Edit</a></td>
                        <td><a class="btn btn-danger" href="<c:url value="/user/delete/${user.id}" />">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h2>No users found</h2>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>