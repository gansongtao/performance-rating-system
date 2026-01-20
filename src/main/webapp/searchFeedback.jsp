<%--
  Created by IntelliJ IDEA.
  User: 15901
  Date: 2025/7/2
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Search Feedbacks</title>
  <link href="style.css" rel="stylesheet"/>
</head>
<body>
  <h1>Performance Rating System for Digitalized Government Procurement Programs</h1>
  <form action="searchFeedback.do" method="Get">
    <label>Username:<input type="text" name="name"/></label><br/>
    <label>Second-level Index:<input type="text" name="second"/></label>
    <input type="submit" value="Submit"/>
  </form>
</body>
</html>
