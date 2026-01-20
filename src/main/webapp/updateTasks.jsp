<%--
  Created by IntelliJ IDEA.
  User: 15901
  Date: 2025/7/2
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Manage Tasks</title>
  <link href="style.css" rel="stylesheet"/>
</head>
<body>
  <h1>Performance Rating System for Digitalized Government Procurement Programs</h1>
  <form action="updateTask.do" method="Get">
    <label>Project ID:<input type="text" name="id"/></label><br/>
    <label>Second-level Indexes:<input type="text" name="second" placeholder="hello,world,you"/></label><br/>
    <label>(New) Start Time:<input type="text" name="start" placeholder="yyyy-mm-dd"/></label><br/>
    <label>(New) End Time:<input type="text" name="end"/></label><br/>
    <label>Old Second-level Index:<input type="text" name="oldSecond"/></label><br/>
    <label>New Second-level Index:<input type="text" name="newSecond"/></label><br/>
    <label>Old Start Time:<input type="text" name="oldStart"/></label><br/>
    <label>Old End Time:<input type="text" name="oldEnd"/></label>
    <label><input type="radio" value="add" name="change"/>Add
      <input type="radio" value="update" name="change"/>Update
      <input type="radio" value="delete" name="change"/>Delete
      <input type="radio" value="search" name="change"/>Search
    </label>
    <input type="submit" value="Submit"/>
  </form>
</body>
</html>
