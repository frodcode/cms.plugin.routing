package frod.routing.service

import frod.routing.domain.Page

class PageService {

    def createPage(PageCommand pageCommand) {
        Page page = new Page()
        page.properties = pageCommand.properties
        return page
    }
}
