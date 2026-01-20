<%--
  Created by IntelliJ IDEA.
  User: 15901
  Date: 2025/5/26
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Performance Rating System for Digitalized Government Procurement Programs</title>
    <link href="style.css" rel="stylesheet"/>
    <script>
        window.onload = function() {
            document.getElementById("root").onclick = function () {
                if (document.getElementById("id").value === "root" && document.getElementById("name").value === "root")
                    window.location.href = "root_index.jsp";
            }
            document.getElementById("admin").onclick = function () {
                document.forms[0].action = "/managerLogin.do";
                document.forms[0].submit();
            }
            document.getElementById("user").onclick = function () {
                document.forms[0].action = "/userLogin.do";
                document.forms[0].submit();
            }
        }
    </script>
</head>
<body>
    <h1>政府采购信息化项目绩效评价系统</h1>
    <form method="GET">
        <label>ID <input type="text" id="id"/></label><br/>
        <label>Name <input type="text" id="name"/></label><br/>
        <input type="submit" value="root" id="root"/>
        <input type="submit" value="admin" id="admin"/>
        <input type="submit" value="user" id="user"/>
    </form>
</body>
</html>
