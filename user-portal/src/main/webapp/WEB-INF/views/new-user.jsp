<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"/>
    <link href="<c:url value='/static/css/shared.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/errors.css' />" rel="stylesheet"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#dob").datepicker();
        });
    </script>
    <title>New User</title>
</head>
<body>
<div class="container-sm">
    <div class="row d-flex justify-content-center mt-4">
        <div class="col-10 col-sm-9 col-md-7 col-lg-5">
            <div class="row d-flex justify-content-center align-items-center">
                <div class="col-1">
                    <a href="<c:url value="/user/all"/>">
                        <img id="back-button" class="mr-4" src="<c:url value="/static/img/svg/chevron-left.svg"/>"
                             alt="" width="28" height="28">
                    </a>
                </div>
                <div class="col-10">
                    <h2 class="text-center"><small>Add New User</small></h2>
                </div>
                <div class="col-1">
                </div>
            </div>
        </div>
    </div>

    <div class="row d-flex justify-content-center mt-2">
        <div class="col-10 col-sm-9 col-md-7 col-lg-5">
            <form:form method="POST" modelAttribute="createUserRequest" class="mt-3">
                <div class="form-group">
                    <label for="firstName">First Name</label>
                    <form:input class="form-control" path="firstName" id="firstName"/>
                    <form:errors path="firstName" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name</label>
                    <form:input class="form-control" path="lastName" id="lastName"/>
                    <form:errors path="lastName" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="countryName">Email</label>
                    <form:input class="form-control" path="email" id="email"/>
                    <form:errors path="email" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <form:password class="form-control" path="password" id="password" />
                    <form:errors path="password" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="gender">Gender</label><br/>
                    Male <form:radiobutton path="gender" value="Male" checked="checked" id="gender"/>
                    Female <form:radiobutton path="gender" value="Female" id="gender"/>
                    <form:errors path="gender" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="dob">Date of birth</label>
                    <form:input class="form-control" path="dob" id="dob" placeholder="Select date" value=""/>
                    <form:errors path="dob" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="address1">Address Line 1</label>
                    <form:input class="form-control" path="address1" id="address1"/>
                    <form:errors path="address1" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="address2">Address Line 2</label>
                    <form:input class="form-control" path="address2" id="address2"/>
                    <form:errors path="address2" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="address3">Address Line 3</label>
                    <form:input class="form-control" path="address3" id="address3"/>
                    <form:errors path="address3" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="address3">City</label>
                    <form:input class="form-control" path="city" id="city"/>
                    <form:errors path="city" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="countryName">Country</label>
                    <form:input class="form-control" path="countryName" id="countryName"/>
                    <form:errors path="countryName" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="postcode">Post code</label>
                    <form:input class="form-control" path="postcode" id="postcode"/>
                    <form:errors path="postcode" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="countryName">Country code</label>
                    <form:input class="form-control" path="countryISOCode" id="countryISOCode"/>
                    <form:errors path="countryISOCode" cssClass="error"/>
                </div>
                <button type="submit" class="btn btn-primary mb-2">Add</button>
            </form:form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"/>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"/>
</body>
</html>
