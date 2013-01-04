package routing

import module.Request
import org.springframework.web.servlet.ModelAndView

import domain.routing.ModuleControl;

class FrontController {

	def routingService;
	
	def callExecutor;

	def route() {
		def page = routingService.findPageByRequest(this.request);
		def viewModel = [:]
		viewModel += callExecutor.executeCalls(applicationContext, page, request, params);
		println viewModel
		if (viewModel) {
			viewModel.article.responses.each {
				if (it.redirects) {
					redirect(uri: it.redirects[0])
				}
				flash.article = it.flashes
			}
		}
		def registredMc = [:]
		page.pageType.moduleControls.each {mc->
			if (!viewModel[mc.slug]) {
				viewModel[mc.slug] = [:]
			}
			viewModel[mc.slug]['mc'] = applicationContext.getBean(mc.className) 
		}
		
		
		viewModel['page'] = page
		return new ModelAndView(page.pageType.templateName, viewModel)
	}
}
