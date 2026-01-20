<%--
  Created by IntelliJ IDEA.
  User: 15901
  Date: 2025/7/2
  Time: 09:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Manage User</title>
    <link href="style.css" rel="stylesheet"/>
</head>
<body>
    <h1>Performance Rating System for Digitalized Government Procurement Programs</h1>
    <form action="updateUser.do" method="Get">
        <label>User ID:<input type="text" name="id"/></label><br>
        <label>Username:<input type="text" name="name"/></label><br>
        <label><input type="radio" value="add" name="change"/>Add
            <input type="radio" value="update" name="change"/>Update
            <input type="radio" value="delete" name="change"/>Delete
            <input type="radio" value="search" name="change"/>Search
        </label>
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
