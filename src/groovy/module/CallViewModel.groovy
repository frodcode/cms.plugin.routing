package module

import domain.routing.Page
import domain.routing.RegisteredCall;

class CallViewModel {

	RegisteredCall moduleCall
	
	def vars = [:]
	
	def response
	
	Page page
	
	Request moduleRequest	
	
}
