import org.codehaus.groovy.grails.commons.ApplicationAttributes

import routing.data.Fixtures
import routing.domain.auth.AuthRole
import routing.domain.auth.AuthUser
import routing.domain.auth.AuthUserAuthRole
import domain.routing.Host

class BootStrap {
	def init = { servletContext ->
		def ctx = servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
		Host defaultHost = new Host(protocol : 'http', domain: 'localhost', port: '8080', domainUrlPart: '/Routing')
		defaultHost.save(failOnError:true)

		def adminRole = new AuthRole(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new AuthRole(authority: 'ROLE_USER').save(flush: true)

		def testUser = new AuthUser(username: 'admin', enabled: true, password: 'password').save(flush: true)

		AuthUserAuthRole.create testUser, adminRole, true

		def fixtures = Fixtures.load(ctx, defaultHost)
		fixtures.pages.adminHomepage.addToAuthRoles(adminRole)
		fixtures.pages.adminHomepage.save()
	}
}
