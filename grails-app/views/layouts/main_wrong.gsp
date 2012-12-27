<html>
    <head>
        <title><g:layoutTitle default="An example decorator" /></title>
        <g:layoutHead />
    </head>
    <body>
        <div class="menu"><!--my common menu goes here-->
        <h2>Menu</h2>
        <ul>
        	<g:render template="menuLevel" model="[currentPage: page.getRoot()]" />
        </ul>
        </menu>
            <div class="body">
                <g:layoutBody />
            </div>
        </div>
    </body>
</html>