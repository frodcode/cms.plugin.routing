<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 28.6.13
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
</head>
<body>
<table>
    <g:each in="${tasks}" var="task">
        <tr>
            <td>
                ${task.id}
            </td>
            <td>${task.name}</td>
            <td><r:link page="${task.page}">Detail</r:link> </td>
        </tr>
    </g:each>
</table>
</body>
</html>