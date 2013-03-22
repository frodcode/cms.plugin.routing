package taglib.routing

class RoutingTagLib {
    def routingService
	static namespace = "r"

	def form = {attrs, body ->
		def page = attrs.page
		def action = attrs.action
        def singletonSlug = attrs.singleton;
		if ((!page && action) || (page && singletonSlug)) {
			throw new IllegalArgumentException('You must define a page to call form action or action itself');
		}
        if (singletonSlug) {
            action = routingService.getSingleton(singletonSlug)?.getLinkFrom(pageScope.page)

        }
        println(action)
		if (page) {
			action = page.getLinkFrom(pageScope.page)
		}
		def attrString = getAllAttrsAsString(attrs)
		out << "<form action='${action}'${attrString}>"+body()+'</form>'
	}

	def textField = { attrs, body ->
		def moduleControls = attrs.controls
		def name = attrs.name
		def nameString = getElementName(moduleControls, name);
        def myAttrs = [name: nameString, id: name, value: attrs.value]

		out << g.textField(myAttrs + attrs)
	}

	/**
	 * @attr page REQUIRED
	 * @attr params
	 */
	def link = {attrs, body ->
		def page = attrs.page
		def params = attrs.params
		def currentPage = pageScope.page;
		if (params && !params instanceof Map) {
			throw new IllegalArgumentException('Params for link must be instance of Map where first level key is control name and second is map of parameters')
		}
		def url = ''
		if (!page) {
			url = 'Error: you must provide page to the link'
		} else {
			url = page?.getLinkFrom(currentPage)
			params.each { it->
				if (it.value instanceof Map == false) {
					throw new IllegalArgumentException(sprintf('Params for link of control "%s" must be instance of Map. E.g. [controlName:[id:4]]', it.key))
				}
				url = getUrlWithParams(it.value, it.key, url)
			}
		}
		def attrString = getAllAttrsAsString(attrs)
		out << "<a href='${url}'${attrString}>"+body()+'</a>'
	}

	private def getUrlWithParams(Map hashMap, String control, String currentUrl) {
		if (!hashMap) {
			return ''
		}
		def linkParams = ''
		if (!currentUrl.contains('?')) {
			linkParams += '?'
		}
		hashMap.each { it ->
			linkParams += control+'_'+it.key + '=' + it.value + '&amp;'
		}
		println linkParams + '-----------'
		linkParams = linkParams[0..-6]
		return currentUrl + linkParams
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
		attrs.each { it-> attrString += " ${it.key}='${it.value}'" }
		return attrString
	}
}
