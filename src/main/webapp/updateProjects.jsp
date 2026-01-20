<%--
  Created by IntelliJ IDEA.
  User: 15901
  Date: 2025/7/2
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Projects</title>
    <link href="style.css" rel="stylesheet"/>
</head>
<body>
    <h1>Performance Rating System for Digitalized Government Procurement Programs</h1>
    <form action="updateProject.do" method="Get">
        <label>Department ID:<input type="text" name="depid"/></label><br/>
        <label>Project Name:<input type="text" name="name"/></label><br/>
        <label>Project ID:<input type="text" name="id"/></label><br/>
        <label>User ID:<input type="text" name="userid"/></label><br/>
        <label>Project Document:<input type="file" id="file" accept="application/msword"/></label>
        <label><input type="radio" value="add" name="change"/>Add
            <input type="radio" value="update" name="change"/>Update
            <input type="radio" value="delete" name="change"/>Delete
            <input type="radio" value="search" name="change"/>Search
        </label>
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
