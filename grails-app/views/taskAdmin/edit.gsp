<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 1.7.13
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="frod.routing.domain.HttpMethodEnum; frod.routing.domain.RequestTypeEnum; frod.routing.domain.Domain; frod.routing.domain.PageType; frod.routing.domain.UrlTypeEnum; frod.routing.domain.Page" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>
    <g:if test="${task}">
        Úkol ${task.name}
    </g:if>
    <g:else>
        Nový úkol
    </g:else>

    </title>


</head>
<body>
<g:form controller="taskAdmin" action="${task ? 'doEdit' : 'doCreate'}" method="post">
    <label for="parentId">Rodič</label><g:select name="parentId" from="${Page.list()}" value="${task?.page?.parentId}" optionKey="id" optionValue="fullUrlPart"/><br/>
    <label for="urlType">Typ adresy</label><g:select name="urlType" from="${['Od rodiče', 'Od domovské stránky']}" keys="${[UrlTypeEnum.FROM_PARENT, UrlTypeEnum.FROM_ROOT]}"/><br/>
    <label for="pageTypeId">Typ stránky</label><g:select name="pageTypeId" from="${PageType.list()}" optionKey="id" optionValue="description"/><br/>
    <label for="domainId">Doména</label><g:select name="domainId" from="${Domain.list()}" optionKey="id" optionValue="url" /> <br/>
    <label for="urlPart">Adresa</label><g:textField name="urlPart" value="${task?.page?.urlPart}" /><br/>
    <label for="langPart">Jazykový prefix</label><g:textField name="langPart" value="${task?.page?.langPart}"/><br/>
    <label for="requestType">Typ požadavku</label><g:select name="requestType" from="${['Normální', 'Ajax']}" keys="${[RequestTypeEnum.REGULAR, RequestTypeEnum.AJAX]}"/><br/>
    <label for="httpMethod">Typ požadavku</label><g:select name="httpMethod" from="${['GET', 'POST', 'PUT']}" keys="${[HttpMethodEnum.GET, HttpMethodEnum.POST, HttpMethodEnum.PUT]}"/><br/>
    <g:hiddenField name="pageId" value="${task?.page.id}" />
    <g:hiddenField name="taskId" value="${task?.id}" />

    <label for="name">Název úkolu</label><g:textField name="name" value="${task?.name}"/>
    <label for="done">Hotovo</label><g:checkBox name="done" value="${task?.done}"/>
    <g:submitButton name="Uložit"/>
</g:form>
</body>
</html>