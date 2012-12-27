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

	/*
	def route() {
		def page = routingService.findPageByRequest(this.request);
		def viewModel = [:]
		page.pageType.registeredCalls.each { el ->
			def moduleControl = applicationContext.getBean(el.moduleControl.className);
			def moduleRequest = this.getRequestFor(el.moduleControl);
			def subViewModel = moduleControl.{
						el.methodName
					}(page, moduleRequest)
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
	
	private def getRequestFor(ModuleControl moduleControl) {
		def requestParams = [:]
		params.each { key, value -> 
			def idParam = moduleControl.slug + '_';
			if (key.toString().startsWith(idParam)) {
				def newKey = key.toString().replace(idParam, '')
				requestParams[newKey] = value
			}
		}
		def newRequest = new Request(full: request, params: requestParams);
		return newRequest;
	}
*/
}
