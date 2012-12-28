package taglib.routing

class RoutingTagLib {
	static namespace = "r"
	
	def form = {attrs, body ->
		def page = attrs.page
		def action = attrs.action
		if (!page && action) {
			throw new IllegalArgumentException('You must define a page to call form action or action itself');
		}
		if (page) {
			action = page.getLinkTo(pageScope.page)
		}
		def attrString = getAllAttrsAsString(attrs)
		out << "<form action='${action}'${attrString}>"+body()+'</form>'
	}
	
	def textField = { attrs, body ->
		def moduleControls = attrs.controls
		def name = attrs.name
		def nameString = getElementName(moduleControls, name);
		out << g.textField(name: nameString, id: name, value: attrs.value)
	}	
	
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
		def attrString = getAllAttrsAsString(attrs)
		out << "<a href='${page.getLinkTo(currentPage)}'${attrString}>"+body()+'</a>'
	}	
	
	private def getElementName(moduleControls, name) {
		def nameString = ''
		if (moduleControls.getClass() == List) {			
			moduleControls.each {it->
				nameString += it.value + '-'
			}
			nameString = nameString.substring(0, -1)
			nameString += '_' + name
		} else {
			nameString = moduleControls + '_' + name
		}
		return nameString
	}
	
	private def getAllAttrsAsString(attrs) {
		def attrString = '';
		attrs.each { it->
			attrString += " ${it.key}='${it.value}'"
		}
		return attrString
	}
		
}
