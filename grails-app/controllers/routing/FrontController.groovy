package routing

import org.springframework.web.servlet.ModelAndView

class FrontController {

	def routingService;

	def route() {
		def page = routingService.findPageByRequest(this.request);
		def viewModel = [:]
		page.pageType.registeredCalls.each { el ->
			def moduleControl = applicationContext.getBean(el.moduleControl.className);
			def subViewModel = moduleControl.{
						el.methodName
					}(page)
			viewModel += subViewModel
		}
		def registredMc = [:]
		page.pageType.moduleControls.each {mc->
			registredMc[mc.slug] = applicationContext.getBean(mc.className)
		}
		viewModel['mc'] = registredMc
		viewModel['page'] = page
		return new ModelAndView(page.pageType.templateName, viewModel)
	}
}
