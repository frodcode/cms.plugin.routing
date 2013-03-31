package routing.example.control

import routing.domain.Page
import routing.call.Request
import routing.call.Response
import routing.example.TaskService

class TaskModuleControl {

    def adminListSlug = 'routing_example_adminlist'

    def adminEditSlug = 'routing_example_adminedit'

    def adminDeleteSlug = 'routing_example_admindelete'

    def frontListSlug = 'routing_example_frontlist'

    def frontDetailSlug = 'routing_example_frontdetail'

    TaskService taskService
	
	public def getTaskList(Page page, Request moduleRequest, Response moduleResponse) {
		return [tasks: taskService.findAll(moduleRequest.getSession())]
	}

    public def edit(Page page, Request moduleRequest, Response moduleResponse)
    {

    }

    public def delete(def page, def moduleRequest, def moduleResponse)
    {

    }

}
