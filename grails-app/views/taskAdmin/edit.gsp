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
    <label for="pageCommand.parentId">Rodič</label><g:select name="pageCommand.parentId" from="${Page.list()}" value="${task?.page?.parentId}" optionKey="id" optionValue="fullUrlPart"/><br/>
    <label for="pageCommand.urlType">Typ adresy</label><g:select name="pageCommand.urlType" from="${['Od rodiče', 'Od domovské stránky']}" keys="${[UrlTypeEnum.FROM_PARENT, UrlTypeEnum.FROM_ROOT]}" value="${task?.page?.urlType}" /><br/>
    <label for="pageCommand.pageTypeId">Typ stránky</label><g:select name="pageCommand.pageTypeId" from="${PageType.list()}" optionKey="id" optionValue="description" value="${task?.page?.pageTypeId}"/><br/>
    <label for="pageCommand.domainId">Doména</label><g:select name="pageCommand.domainId" from="${Domain.list()}" optionKey="id" optionValue="url"  value="${task?.page?.domainId}" /> <br/>
    <label for="pageCommand.urlPart">Adresa</label><g:textField name="pageCommand.urlPart" value="${task?.page?.urlPart}" /><br/>
    <label for="pageCommand.langPart">Jazykový prefix</label><g:textField name="pageCommand.langPart" value="${task?.page?.langPart}"/><br/>
    <label for="pageCommand.requestType">Typ požadavku</label><g:select name="pageCommand.requestType" from="${['Normální', 'Ajax']}" keys="${[RequestTypeEnum.REGULAR, RequestTypeEnum.AJAX]}" value="${task?.page?.requestType}"/><br/>
    <label for="pageCommand.httpMethod">Typ požadavku</label><g:select name="pageCommand.httpMethod" from="${['GET', 'POST', 'PUT']}" keys="${[HttpMethodEnum.GET, HttpMethodEnum.POST, HttpMethodEnum.PUT]}" value="${task?.page?.httpMethod}"/><br/>
    <g:hiddenField name="pageCommand.pageId" value="${task?.page?.id}" />
    <g:hiddenField name="taskId" value="${task?.id}" />

    <label for="name">Název úkolu</label><g:textField name="name" value="${task?.name}"/>
    <label for="done">Hotovo</label><g:checkBox name="done" value="${task?.done}"/>
    <g:submitButton name="Uložit"/>
</g:form>
</body>
</html>