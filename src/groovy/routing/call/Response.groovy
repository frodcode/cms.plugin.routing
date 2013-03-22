package routing.call

import routing.domain.Page

class Response {

	def calls = []

	def flashes = [:]

	def redirects = []

	def addCall(def callClosure) {
		calls.add(callClosure)
	}

	def addFlash(String key, String value) {
		flashes[key] = value
	}

	def addRedirect(Page page) {
		redirects.add(page.url)
	}
}
