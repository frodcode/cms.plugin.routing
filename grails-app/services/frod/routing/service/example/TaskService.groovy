package frod.routing.service.example

import frod.routing.service.PageCommand
import frod.routing.service.PageService
import frod.routing.domain.Page
import frod.routing.domain.example.Task

class TaskService {

    PageService pageService

    def create(PageCommand pageCommand, TaskCommand taskCommand) {
        Page page = pageService.createPage(pageCommand)
        Task task = new Task()
        task.properties = taskCommand.properties
        task.page = page
        task.save(flush: true)
    }

    def edit(PageCommand pageCommand, TaskCommand taskCommand) {
        Page page = pageService.editPage(pageCommand)
        Task task = taskCommand.task
        task.properties = taskCommand.properties
        task.page = page
        task.save(flush: true)
    }

}
