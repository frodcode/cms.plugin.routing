package frod.routing.service.example

import frod.routing.service.PageCommand
import frod.routing.service.PageService
import frod.routing.domain.Page
import frod.routing.domain.example.Task

import org.codehaus.groovy.grails.web.binding.DataBindingUtils;

class TaskService {

    PageService pageService

    def create(TaskCommand taskCommand) {
        Page page = pageService.createPage(taskCommand.pageCommand)
        Task task = new Task()
        DataBindingUtils.bindObjectToInstance(task, taskCommand, [], ['task'], null)
        task.page = page
        task.save(flush: true)
    }

    def edit(TaskCommand taskCommand) {
        Page page = pageService.editPage(taskCommand.pageCommand)
        Task task = taskCommand.task
        task.properties = taskCommand.properties
        task.page = page
        task.save(flush: true)
    }

}
