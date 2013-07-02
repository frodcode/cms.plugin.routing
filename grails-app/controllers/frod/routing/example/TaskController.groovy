package frod.routing.example

import frod.routing.domain.Page
import frod.routing.domain.example.Task
import frod.routing.service.PageService
import frod.routing.service.RoutingService

class TaskController {

    def list(long pageId) {
        return [tasks: Task.list()]
    }

    def detail(long pageId) {
        return [task: Task.find{page.id == pageId}]
    }
}
