package routing.call

import org.springframework.context.*

import routing.domain.ModuleControl
import routing.domain.Page

class CallExecutor {

	public def executeCalls(ApplicationContext applicationContext, Page page, controllerRequest, controllerParams) {
		def callViewModels = []
		page.pageType.registeredCalls.each { moduleCall ->
			def callViewModel = new CallViewModel();

			def moduleControl = applicationContext.getBean(moduleCall.moduleControl.className);
			def moduleRequest = this.getRequestFor(moduleCall.moduleControl, controllerRequest, controllerParams);
			def moduleResponse = new Response()
			def vars = moduleControl.{
						moduleCall.methodName
					}(page, moduleRequest, moduleResponse)
			if (vars instanceof Map) {
				callViewModel.setVars(vars)
			}
			moduleResponse.calls.each { it ->
				if (!it) {
					throw new IllegalStateException('Registered call is not callable (is probably null). Check call trace.')
				}
				it(moduleResponse, moduleRequest, page);
			}
			callViewModel.response = moduleResponse
			callViewModel.page = page
			callViewModel.moduleCall = moduleCall
			callViewModels.add(callViewModel)
		}
		return callViewModels
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