<%--
  Created by IntelliJ IDEA.
  User: 15901
  Date: 2025/7/6
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Hello ${requestScope.name}</title>
</head>
<body>
    <h2>Scores:</h2>
    <table>
        <tr>
            <td>Course</td>
            <td>Credit</td>
            <td>Score</td>
        </tr>
        <c:forEach var="item" items="${requestScope.myScore}">
            <tr>
                <td>${item.course}</td>
                <td>${item.credit}</td>
                <td>${item.score}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Courses to choose:</h2>
    <table>
        <tr>
            <td>Course</td>
            <td>Term</td>
        </tr>
        <c:forEach var="item" items="${requestScope.choices}">
            <tr>
                <td>${item.course}</td>
                <td>${item.term}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
