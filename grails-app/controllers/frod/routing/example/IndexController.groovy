package frod.routing.example

import ford.routing.domain.Page

class IndexController {

    def index() {
        Page page = Page.get(params.pageId)
        return [page: page]
    }
}
