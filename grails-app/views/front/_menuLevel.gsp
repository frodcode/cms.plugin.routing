<li><a href="${currentPage.url}">${currentPage.url}</a></li>   
<g:if test="${currentPage.subPages.size() > 0}">
	<ul>
		<g:each in="${currentPage.subPages}" var="subPage">
			<g:render template="menuLevel" model="[currentPage: subPage]" />
		</g:each>
	</ul>
</g:if>
