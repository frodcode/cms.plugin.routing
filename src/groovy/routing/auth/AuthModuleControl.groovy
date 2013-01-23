package routing.auth

class AuthModuleControl {

	def doLoginSlug = 'auth_admin_do_login'
	def loginSlug = 'auth_admin_login'

	public def doLogin(def page, def moduleRequest, def moduleResponse) {
		return [:]
	}
}
