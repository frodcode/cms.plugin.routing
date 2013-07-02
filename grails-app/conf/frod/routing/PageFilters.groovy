package frod.routing

import frod.routing.domain.Page

class PageFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {

            }
            after = { Map model ->
                if (request.getParameter('pageId') && model) {
                    model.page = Page.get(request.getParameter('pageId'))
                }
            }
            afterView = { Exception e ->

            }
        }
    }
}
