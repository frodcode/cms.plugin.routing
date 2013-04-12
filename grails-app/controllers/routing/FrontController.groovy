package routing

import org.springframework.web.servlet.ModelAndView

import routing.call.CallExecutor
import routing.call.CallViewModel
import routing.control.auth.AuthModuleControl
import routing.domain.Page
import routing.auth.AclService
import routing.auth.IAclAction
import routing.auth.AclRedirectAction

class FrontController {

	RoutingService routingService;

	CallExecutor callExecutor;

	AuthModuleControl authModuleControl

    def springSecurityService

    def aclService

	static layout = 'admin'

	def route() {
		Page page = routingService.findPageByRequest(this.request);
		if (!page) {
			throw new IllegalArgumentException('Cannot find page by params '+this.request.getParameterMap() + ' and method '+this.request.getMethod()+' on url '+this.request.forwardURI)
		}
        if (!routingService.getCompleteUrl(request).equals(page.url) && !page.isRoot()) {
            redirect(uri: page.url);
        }
		if (page.authRoles) {
            IAclAction aclAction
			if (!springSecurityService.isLoggedIn()) {
                aclAction = aclService.getRedirectAddressOnNotLoggedIn(page)
			}else if (!page.authRoles.any { pageRole -> return !!springSecurityService.getCurrentUser().getAuthorities().find {authRole -> authRole.id == pageRole.id}}) {
                aclAction = aclService.getActionOnNotEnoughPrivileges(page)
            }
            if (aclAction) {
                applyAclAction(aclAction);
            }
		}

		def callViewModels = []
        if (!session.mc) {
            session.mc = [:]
        }
		callViewModels += callExecutor.executeCalls(applicationContext, page, request, params, session.mc);
		if (callViewModels) {
			callViewModels*.response.each {
				if (it.redirects) {
					redirect(uri: it.redirects[0])
				}
                if (it.flashes) {
				    flash.messages = it.flashes
                }
			}
		}
		def viewModel = [:]
		page.pageType.moduleControls.each {mc->
			if (!viewModel[mc.slug]) {
				viewModel[mc.slug] = [:]
			}
			viewModel[mc.slug]['mc'] = applicationContext.getBean(mc.className)
			viewModel[mc.slug]['vars'] = getAllVarsForModuleControl(callViewModels, mc.slug)
            viewModel[mc.slug]['session'] = session.mc
		}

		viewModel['page'] = page

		return new ModelAndView(page.pageType.templateName, viewModel)
	}

    private void applyAclAction(AclRedirectAction aclAction)
    {
        redirect(uri:aclAction.url)
    }

	private LinkedHashMap getAllVarsForModuleControl(List<CallViewModel> callViewModels, String moduleControlSlug) {
		def allVars = [:]
		callViewModels.each { it ->
			if (it.moduleCall.moduleControl.slug == moduleControlSlug) {
				allVars += it.vars
			}
		}
		return allVars
	}
}
