<%--
  Created by IntelliJ IDEA.
  User: 15901
  Date: 2025/6/27
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Users</title>
  <link href="style.css" rel="stylesheet"/>
</head>
<body>
  <h1>Users</h1>
  <p>Finish Tasks</p>
  <form action="finishTask.do" method="Get">
    <label>Second-level Index: <input type="text" id="second"/></label><br/>
    <label>Result: <input type="number" id="result" placeholder="5"/></label>
    <input type="hidden" id="id" value="${requestScope.id}"/>
    <input type="submit" value="Submit"/>
  </form>
  <p>Your information:</p>
  <p>Department: ${requestScope.depname}</p>
  <p>Project: ${requestScope.projname}</p>
  <table>
    <tr>
      <td>Second-level Index</td>
      <td>Ratings</td>
      <td>Administrator Answer</td>
    </tr>
    <c:forEach var="item" items="${requestScope.secondhand}" varStatus="status">
      <tr>
        <td>${item}</td>
        <td>${requestScope.result[status.index]}</td>
        <td>${requestScope.answer[status.index]}</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>
