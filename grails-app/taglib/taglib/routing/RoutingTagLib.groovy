package taglib.routing

class RoutingTagLib {
	static namespace = "r"
	
	/**
	 * @attr page REQUIRED
	 */
	def link = {attrs, body ->
		def page = attrs.page
		def currentPage = pageScope.page;
		def url = page.url
		if (!page) {
			url = 'Error: you must provide page to the link'
		}
		def attrString = ''
		attrs.each { it->
			attrString += " ${it.key}='${it.value}'"
		}
		out << "<a href='${page.getLinkTo(currentPage)}'${attrString}>"+body()+'</a>'
	}
}
