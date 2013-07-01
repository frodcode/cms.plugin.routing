<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 1.7.13
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Tasks</title>
</head>
<body>
<h1>Tasks</h1>
<g:link controller="taskAdmin" action="newTask">Vytvořit nový</g:link>
<table>
    <g:each in="${tasks}" var="task">
        <tr>
            <td>${task.id}</td>
            <td>${task.name}</td>
            <td>${task.done ? 'hotovo' : 'chybí'}</td>
            <td><g:link controller="TaskAdmin" action="edit" params="[id: task.id]">editovat</g:link></td>
        </tr>
    </g:each>
</table>
</body>
</html>