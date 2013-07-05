import org.codehaus.groovy.grails.commons.ApplicationAttributes

import routing.data.Fixtures
import routing.data.ExampleData
import frod.routing.domain.Domain
import grails.util.Environment
import routing.data.TestData

class BootStrap {
	def init = { servletContext ->
		def ctx = servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
		Domain defaultDomain = new Domain(protocol : 'http', host: 'localhost', port: '8080', domainUrlPart: '/Routing')
		defaultDomain.save(failOnError:true)

		def fixtures = Fixtures.load(ctx, defaultDomain)

        if (Environment.current == Environment.TEST) {
            TestData.load(ctx, defaultDomain, fixtures)
        } else {
            ExampleData.load(ctx, defaultDomain, fixtures)
        }
	}
    def destroy = {

    }
}
