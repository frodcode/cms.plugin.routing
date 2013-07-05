package frod.routing.example.admin

import frod.routing.domain.example.Task
import org.springframework.web.servlet.ModelAndView
import frod.routing.service.PageCommand
import frod.routing.service.example.TaskCommand
import frod.routing.service.example.TaskService


class TaskAdminController {

    TaskService taskService

    def edit() {
        return [task: Task.get(params.id)]
    }

    def doEdit(TaskCommand taskCommand) {
        taskService.edit(taskCommand)
        redirect(action: 'list')
    }

    def list() {
        return [tasks: Task.findAll()]
    }

    def newTask() {
        return new ModelAndView("/taskAdmin/edit", [:])
    }

    def doCreate(TaskCommand taskCommand) {
        taskService.create(taskCommand)
        redirect(action: 'list')
    }

}
