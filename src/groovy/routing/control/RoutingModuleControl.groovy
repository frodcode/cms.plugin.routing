package routing.control

import domain.routing.Page

class RoutingModuleControl {
	
	def routingService;
	
	public Page findPageBySingletonType(String type) {
		return routingService.findPageBySingletonType(type)
	}

}
