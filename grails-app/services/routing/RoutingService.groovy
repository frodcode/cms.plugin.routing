package routing

import javax.servlet.http.HttpServletRequest

import routing.domain.HttpMethodEnum
import routing.domain.Page
import routing.domain.PageType
import routing.domain.RequestTypeEnum


class RoutingService {

	public String getCompleteUrl(HttpServletRequest requestObject) {
		def url = (requestObject.scheme + "://" + requestObject.serverName + ":" + requestObject.serverPort + requestObject.forwardURI).trim()
        return url;
	}

	private RequestTypeEnum getRequestTypeByRequest(HttpServletRequest requestObject) {
		if (requestObject.getHeader('X-Requested-With')) {
			return RequestTypeEnum.AJAX;
		}
		return RequestTypeEnum.REGULAR;
	}

	private HttpMethodEnum getHttpMethodByRequest(HttpServletRequest requestObject) {
		switch (requestObject.getMethod()) {
			case 'GET':
				return HttpMethodEnum.GET
			case 'POST':
				return HttpMethodEnum.POST
			case 'PUT':
				return HttpMethodEnum.PUT
		}
		throw new IllegalArgumentException(String.format('Undefined request method "%s"', requestObject.getMethod()))
	}

	public Page findPageByRequest(HttpServletRequest requestObject) {
        def url = getCompleteUrl(requestObject)
        if (url.charAt(url.length()-1) == '/') {
            url = url[0..-2]
        }
		def page = Page.find("FROM Page as p INNER JOIN FETCH p.pageType pt LEFT JOIN FETCH pt.moduleControls mc WHERE p.url = :url AND p.httpMethod = :httpMethod AND p.requestType = :requestType",
				[url: url, httpMethod: getHttpMethodByRequest(requestObject), requestType: getRequestTypeByRequest(requestObject)])
		return page
	}

	public Page getSingleton(String type) {
		Page page = Page.find("FROM Page as p INNER JOIN FETCH p.pageType pt LEFT JOIN FETCH pt.moduleControls mc WHERE p.pageType.slug = :type AND p.pageType.singleton = :singleton", [type: type, singleton: true])
		if (!page) {
			throw new IllegalArgumentException(sprintf('Cannot find singleton page with type "%s"', type))
		}
		return page
	}

	public PageType getSingletonType(String type) {
		return PageType.findBySlug(type)
	}
}
