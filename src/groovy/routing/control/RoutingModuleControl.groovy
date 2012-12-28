package routing.control

import domain.routing.Page
import domain.routing.PageType

class RoutingModuleControl {
	
	def routingService;
	
	public Page getSingleton(String type) {
		return routingService.getSingleton(type)
	}
	
	public PageType getSingletonType(String type) {
		return routingService.getSingletonType(type)
	}

}
