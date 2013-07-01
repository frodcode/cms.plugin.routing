import org.codehaus.groovy.grails.commons.ApplicationAttributes

import routing.data.Fixtures
import routing.data.ExampleData
import frod.routing.domain.Domain

class BootStrap {
	def init = { servletContext ->
		def ctx = servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
		Domain defaultHost = new Domain(protocol : 'http', host: 'localhost', port: '8080', domainUrlPart: '/Routing')
		defaultHost.save(failOnError:true)

		def fixtures = Fixtures.load(ctx, defaultHost)
        def exampleData = ExampleData.load(ctx, defaultHost, fixtures)
	}
    def destroy = {

    }
}
