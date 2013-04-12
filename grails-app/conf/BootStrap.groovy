import org.codehaus.groovy.grails.commons.ApplicationAttributes

import routing.data.Fixtures
import routing.domain.Host
import routing.data.ExampleData

class BootStrap {
	def init = { servletContext ->
		def ctx = servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
		Host defaultHost = new Host(protocol : 'http', domain: 'localhost', port: '8080', domainUrlPart: '/Routing')
		defaultHost.save(failOnError:true)

		def fixtures = Fixtures.load(ctx, defaultHost)
        def exampleData = ExampleData.load(ctx, defaultHost, fixtures)
	}
}
