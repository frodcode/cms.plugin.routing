package frod.routing.service

import frod.routing.domain.Page

class PageService {

    RoutingService routingService

    def createPage(PageCommand pageCommand) {
        Page page = new Page()
        page.properties = pageCommand.properties
        page.save(flush: true)
        return page
    }

    def editPage(PageCommand pageCommand) {
        Page page = pageCommand.page
        String oldUrl = page.url
        page.properties = pageCommand.properties
        page.regenerateUrl()
        routingService.tryCreateRedirect(oldUrl, page.url)
        return page
    }
}
