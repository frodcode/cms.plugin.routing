package module

import domain.routing.ModuleControl;
import domain.routing.Page

class CallExecutor {
	
	public def executeCalls(Page page, controllerRequest, controllerParams) {
		def viewModel = [:]
		page.pageType.registeredCalls.each { el ->
			def moduleControl = applicationContext.getBean(el.moduleControl.className);
			def moduleRequest = this.getRequestFor(el.moduleControl, controllerRequest, controllerParams);
			def subViewModel = moduleControl.{
						el.methodName
					}(page, moduleRequest)
			viewModel += subViewModel
		}
		return viewModel
	}
	
	private def getRequestFor(ModuleControl moduleControl, controllerRequest, controllerParams) {
		def requestParams = [:]
		controllerParams.each { key, value ->
			def idParam = moduleControl.slug + '_';
			if (key.toString().startsWith(idParam)) {
				def newKey = key.toString().replace(idParam, '')
				requestParams[newKey] = value
			}
		}
		def newRequest = new Request(full: controllerRequest, params: requestParams);
		return newRequest;
	}
	
}
