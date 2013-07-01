package frod.routing.example

import frod.routing.domain.Page

class IndexController {

    def index() {
        Page page = Page.get(params.pageId)
        Page taskList = Page.find{pageType.slug == 'task_list'}
        return [page: page, taskListPage: taskList]
    }
}
