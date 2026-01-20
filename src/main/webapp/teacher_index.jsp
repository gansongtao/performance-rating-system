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
    <form action="teacherServlet.do" method="GET">
        <label>Student ID: <input type="text" id="stuID"/></label>
        <label>Score: <input type="text" id="score"/></label>
        <input type="hidden" value="${requestScope.name}"/>
        <input type="submit" id="name" value="Submit"/>
    </form>
    <p>Student ID: ${requestScope.stuID}</p>
    <p>Credit Now: ${requestScope.totalCredit}</p>
    <p>Your Schedule</p>
    <table>
        <tr>
            <td>Course</td>
            <td>Year</td>
            <td>Term</td>
            <td>Credit</td>
        </tr>
        <c:forEach var="item" items="${requestScope.schedule}">
            <tr>
                <td>${item.course}</td>
                <td>${item.year}</td>
                <td>${item.term}</td>
                <td>${item.credit}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
