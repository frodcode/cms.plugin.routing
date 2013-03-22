package routing.control.auth

import org.springframework.security.authentication.BadCredentialsException

import routing.AuthService
import routing.RoutingService
import routing.domain.Page

class AuthModuleControl {

	def doLoginSlug = 'auth_admin_do_login'
	def loginSlug = 'auth_admin_login'
	def adminHomepageSlug = 'admin_homepage'

	RoutingService routingService;
	AuthService authService

	def onSuccess = { moduleResponse, moduleRequest, page ->
		Page redirectPage = routingService.getSingleton(adminHomepageSlug);
		moduleResponse.addFlash('message', 'You are succesfully logged in')
		moduleResponse.addRedirect(redirectPage)
	}

	def onDenied = { moduleResponse, moduleRequest, page ->
		Page redirectPage = routingService.getSingleton(loginSlug);
		moduleResponse.addFlash('error', 'Wrong username or password')
        println 'incorrect login'
		moduleResponse.addRedirect(redirectPage)
	}

	public def doLogin(def page, def moduleRequest, def moduleResponse) {
		try {
			// Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
			def username = moduleRequest.username
			def password = moduleRequest.password
			authService.login(username, password, moduleRequest.getFull())
			moduleResponse.addCall(this.onSuccess)
		} catch (BadCredentialsException e) {
			moduleResponse.addCall(this.onDenied)
		}
		return [:]
	}
}
