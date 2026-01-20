<%--
  Created by IntelliJ IDEA.
  User: 15901
  Date: 2025/7/2
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Manage Indexes</title>
  <link href="style.css" rel="stylesheet"/>
</head>
<body>
  <h1>Performance Rating System for Digitalized Government Procurement Programs</h1>
  <form action="updateindex.do" method="Get">
    <label>First-level Index:<input type="text" name="first"/></label><br/>
    <label>Second-level Index:<input type="text" name="second"/></label>
    <label><input type="radio" value="add" name="change"/>Add
      <input type="radio" value="del2" name="change"/>Delete Second-level
      <input type="radio" value="del1" name="change"/>Delete First-level
      <input type="radio" value="search" name="change"/>Search Second-level Indexes
    </label>
    <input type="submit" value="Submit"/>
  </form>
</body>
</html>
