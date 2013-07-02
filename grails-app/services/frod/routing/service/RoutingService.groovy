package frod.routing.service

import javax.servlet.http.HttpServletRequest

import frod.routing.domain.HttpMethodEnum
import frod.routing.domain.Page
import frod.routing.domain.PageType
import frod.routing.domain.RequestTypeEnum
import frod.routing.domain.Domain
import frod.routing.domain.Redirect


class RoutingService {

	public String getCompleteUrl(HttpServletRequest requestObject) {
		def url = (requestObject.scheme + "://" + requestObject.serverName + ":" + requestObject.serverPort + requestObject.forwardURI).trim()
        if (!isRootPage(requestObject) && url.charAt(url.length()-1) == '/') {
            url = url[0..-2]
        }
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

    private boolean isRootPage(HttpServletRequest requestObject) {
        String trimPart = requestObject.forwardURI
        if (trimPart.endsWith('/')) {
            trimPart = trimPart[0..-2]
        }

        def domain = Domain.find {protocol == requestObject.scheme && host == requestObject.serverName && port == requestObject.serverPort && domainUrlPart == trimPart}
        return !!domain
    }

	public Page findPageByRequest(HttpServletRequest requestObject) {
        def url = getCompleteUrl(requestObject)
		def page = Page.find("FROM Page as p INNER JOIN FETCH p.pageType pt WHERE p.url = :url AND p.httpMethod = :httpMethod AND p.requestType = :requestType",
				[url: url, httpMethod: getHttpMethodByRequest(requestObject), requestType: getRequestTypeByRequest(requestObject)])
		return page
	}

	public Page getSingleton(String type) {
		Page page = Page.find("FROM Page as p INNER JOIN FETCH p.pageType pt WHERE p.pageType.slug = :type AND p.pageType.singleton = :singleton", [type: type, singleton: true])
		if (!page) {
			throw new IllegalArgumentException(sprintf('Cannot find singleton page with type "%s"', type))
		}
		return page
	}

	public PageType getSingletonType(String type) {
		return PageType.findBySlug(type)
	}

    public void tryCreateRedirect(String oldUrl, String newUrl)
    {
        if (oldUrl != newUrl) {
            Redirect redirect
            redirect = Redirect.findByFromUrl(oldUrl)
            if (!redirect) {
                redirect = new Redirect(fromUrl: oldUrl, permanent: true)
            }
            def loopingRedirects = Redirect.find {
                toUrl == oldUrl
            }
            loopingRedirects*.toUrl = newUrl
            redirect.toUrl = newUrl
            loopingRedirects*.save()
            redirect.save(flush: true)
        }
    }

    public Redirect findRedirect(HttpServletRequest requestObject)
    {
        def url = getCompleteUrl(requestObject)
        return Redirect.findByFromUrl(url)

    }

}
