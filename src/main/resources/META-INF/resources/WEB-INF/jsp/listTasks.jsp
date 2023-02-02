<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="common/navigation.jspf" %>
<div class="container">
<h3>Welcome ${username}</h3>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Description</th>
        <th scope="col">Completion Date</th>
        <th scope="col">Done</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>${task.id}</td>
            <td>${task.description}</td>
            <td>${task.completionDate}</td>
            <td>${task.complete}</td>
            <td><a href="removeTask?id=${task.id}" class="btn btn-danger">Delete</a></td>
            <td><a href="updateTask?id=${task.id}" class="btn btn-primary">Update</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="addTask" class="btn btn-success">Add New Task</a>
</div>
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>