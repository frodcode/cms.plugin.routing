package routing

import module.CallViewModel

import org.springframework.web.servlet.ModelAndView

class FrontController {

	def routingService;

	def callExecutor;

	def route() {
		def page = routingService.findPageByRequest(this.request);
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
