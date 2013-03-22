package routing.control

import routing.domain.Page
import routing.domain.PageType


class RoutingModuleControl {

	def routingService;

	public Page getSingleton(String type) {
		return routingService.getSingleton(type)
	}

	public PageType getSingletonType(String type) {
		return routingService.getSingletonType(type)
	}
}
