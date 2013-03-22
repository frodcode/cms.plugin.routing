package routing.call

import routing.domain.Page
import routing.domain.RegisteredCall


class CallViewModel {

	RegisteredCall moduleCall

	def vars = [:]

	def response

	Page page

	Request moduleRequest
}
