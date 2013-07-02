<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 1.7.13
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
</head>
<body>
<h1>Deatil pro task s id ${task.id}</h1>
<div>
    <p>${task.name}</p>
    <p>${task.done ? 'hotovo' : 'chybí'}</p>
    <p><r:link singleton="task_list">Zpět</r:link> </p>
</div>
</body>
</html>