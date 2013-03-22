package routing

import org.springframework.web.servlet.ModelAndView

import routing.call.CallExecutor
import routing.call.CallViewModel
import routing.control.auth.AuthModuleControl
import routing.domain.Page

class FrontController {

	RoutingService routingService;

	CallExecutor callExecutor;

	AuthModuleControl authModuleControl

	static layout = 'admin'

	def route() {
		Page page = routingService.findPageByRequest(this.request);
		if (!page) {
			throw new IllegalArgumentException('Cannot find page by params '+this.request.getParameterMap() + ' and method '+this.request.getMethod()+' on url '+this.request.forwardURI)
		}
		if (page.authRoles) {
			if (!isLoggedIn()) {
				Page redirectPage = this.routingService.getSingleton(this.authModuleControl.loginSlug);
				redirect(uri: redirectPage.url)
			}
		}
		/*if (!isLoggedIn()) {
		 throw new IllegalAccessError('Not permited')
		 }*/
		def callViewModels = []
		callViewModels += callExecutor.executeCalls(applicationContext, page, request, params);
		if (callViewModels) {
			callViewModels*.response.each {
				if (it.redirects) {
					redirect(uri: it.redirects[0])
				}
				flash.messages = it.flashes
			}
		}
		def viewModel = [:]
		page.pageType.moduleControls.each {mc->
			if (!viewModel[mc.slug]) {
				viewModel[mc.slug] = [:]
			}
			viewModel[mc.slug]['mc'] = applicationContext.getBean(mc.className)
			viewModel[mc.slug]['vars'] = getAllVarsForModuleControl(callViewModels, mc.slug)
		}

		viewModel['page'] = page

		return new ModelAndView(page.pageType.templateName, viewModel)
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
