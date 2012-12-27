package routing

import javax.servlet.http.HttpServletRequest

import domain.routing.HttpMethodEnum
import domain.routing.Page
import domain.routing.RequestTypeEnum

class RoutingService {

	
	private String getCompleteUrl(HttpServletRequest requestObject)
	{
		return (requestObject.scheme + "://" + requestObject.serverName + ":" + requestObject.serverPort + requestObject.forwardURI)
	}
	
	private RequestTypeEnum getRequestTypeByRequest(HttpServletRequest requestObject)
	{
		if (requestObject.getHeader('X-Requested-With')) {
			return RequestTypeEnum.AJAX;
		}
		return RequestTypeEnum.REGULAR;
	}
	
	private HttpMethodEnum getHttpMethodByRequest(HttpServletRequest requestObject)
	{
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
	
    public Page findPageByRequest(HttpServletRequest requestObject)
	{
		def page = Page.find("FROM Page as p WHERE p.url = :url AND p.httpMethod = :httpMethod AND p.requestType = :requestType", 
			[url: getCompleteUrl(requestObject), httpMethod: getHttpMethodByRequest(requestObject), requestType: getRequestTypeByRequest(requestObject)]);
		return page
	}
	
	public Page findPageBySingletonType(String type) {
		Page page = Page.find("FROM Page as p WHERE p.pageType.slug = :type AND p.pageType.singleton = :singleton", [type: type, singleton: true])		
		return page
	}
}
