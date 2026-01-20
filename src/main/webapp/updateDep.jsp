<%--
  Created by IntelliJ IDEA.
  User: 15901
  Date: 2025/7/2
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Manage Departments</title>
    <link href="style.css" rel="stylesheet"/>
</head>
<body>
    <h1>Performance Rating System for Digitalized Government Procurement Programs</h1>
    <form action="updateDep.do" method="Get">
        <label>Department ID:<input type="text" name="id"/></label><br>
        <label>Department Name:<input type="text" name="name"/></label><br>
        <label>Project ID:<input type="text" name="projid"/></label>
        <label><input type="radio" value="add" name="change"/>Add
            <input type="radio" value="update" name="change"/>Update
            <input type="radio" value="delete" name="change"/>Delete
            <input type="radio" value="search" name="change"/>Search for Department
            <input type="radio" value="all" name="change"/>Search for User
        </label>
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
