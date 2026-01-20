<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 15901
  Date: 2025/7/7
  Time: 00:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Curriculum Average Score</title>
</head>
<body>
  <table>
    <tr>
      <td>Course</td>
      <td>Average</td>
    </tr>
    <c:forEach var="item" items="${requestScope.curriculum}">
      <tr>
        <td>${item.gst_course}</td>
        <td>${item.column}</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>
