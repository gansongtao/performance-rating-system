<%--
  Created by IntelliJ IDEA.
  User: 15901
  Date: 2025/5/12
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>修改客户</title>
    <link href="style.css" rel="stylesheet"/>
</head>
<body>
    <p>Please enter the changed admin</p>
    <form action="updateAdmin.do" method="Get">
        <table>
            <tr>
                <td>Admin ID:</td>
                <td><label><input type="text" name="id"/></label></td>
            </tr>
            <tr>
                <td>Admin Name:</td>
                <td><label><input type="text" name="cname"/></label></td>
            </tr>
            <tr>
                <td><input type="submit" value="确定"/></td>
            </tr>
        </table>
    </form>
</body>
</html>
