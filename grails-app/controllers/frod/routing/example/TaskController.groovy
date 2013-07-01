package frod.routing.example

import frod.routing.domain.Page
import frod.routing.domain.example.Task

class TaskController {

    def list(long pageId) {
        return [tasks: Task.list(), page: Page.get(pageId)]
    }

    def detail(long pageId) {
        return [task: Task.find{page.id == pageId}]
    }
}
