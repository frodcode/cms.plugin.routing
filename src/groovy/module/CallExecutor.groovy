package module

import domain.routing.ModuleControl;
import domain.routing.Page

import org.springframework.beans.BeansException;
import org.springframework.context.*

class CallExecutor {
	
	
	public def executeCalls(ApplicationContext applicationContext, Page page, controllerRequest, controllerParams) {
		def viewModel = [:]
		page.pageType.registeredCalls.each { el ->			
			if (!viewModel[el.moduleControl.slug]) {
				viewModel[el.moduleControl.slug] = [:]
				viewModel[el.moduleControl.slug]['vars'] = [:]
				viewModel[el.moduleControl.slug]['responses'] = []
			}
			def moduleControl = applicationContext.getBean(el.moduleControl.className);
			def moduleRequest = this.getRequestFor(el.moduleControl, controllerRequest, controllerParams);
			def moduleResponse = new Response()
			def subViewModel = moduleControl.{
						el.methodName
					}(page, moduleRequest, moduleResponse)
			moduleResponse.calls.each { it ->
				it(moduleResponse);
			}
			viewModel[el.moduleControl.slug].responses.add(moduleResponse)
			viewModel[el.moduleControl.slug]['vars'] += subViewModel
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
