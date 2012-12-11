<html>
<head>
<title>Novinky</title>
<meta name="layout" content="main" />
</head>
<body>
	<h1>Novinky</h1>
	<p>
		${mc.article.getArticleText()}
	</p>
	<ul>
		<g:each var="related" in="${mc.article.getRelatedArticles()}">
			<li>
				${related }
			</li>
		</g:each>
	</ul>
</body>
</html>

