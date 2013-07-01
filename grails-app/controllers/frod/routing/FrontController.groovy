package frod.routing

import frod.routing.domain.Page
import frod.routing.service.RoutingService

class FrontController {

	RoutingService routingService;

    def springSecurityService

    def aclService

	static layout = 'admin'

	def route() {
		Page page = routingService.findPageByRequest(this.request);
		if (!page) {
            throw new IllegalArgumentException('Cannot find page by params ' + this.request.getParameterMap() + ' and method ' + this.request.getMethod() + ' on url ' + this.request.forwardURI)
        }
        if (!routingService.getCompleteUrl(request).equals(page.url) && !page.isRoot()) {
            redirect(uri: page.url);
        }
		if (page.authRoles) {
            def aclAction
			if (!springSecurityService.isLoggedIn()) {
                // forward to login ctrl
			}else if (!page.authRoles.any { pageRole -> return !!springSecurityService.getCurrentUser().getAuthorities().find {authRole -> authRole.id == pageRole.id}}) {
               // forward to not engough priv ctrl
            }
		}
        def forwardParams = [controller: page.pageType.controller.toLowerCase(), action: page.pageType.action.toLowerCase(), params: [pageId: page.id]]

        forward(forwardParams)
	}

}
