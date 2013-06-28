package frod.routing.example

import ford.routing.domain.Page

class TaskController {

    def list() {
        return [page: Page.get(params.pageId)]
    }
}
