package routing.example.control

import routing.domain.Page
import routing.call.Request
import routing.call.Response
import routing.example.TaskService
import routing.example.domain.Task
import routing.RoutingService

class TaskModuleControl {

    def adminListSlug = 'routing_example_adminlist'

    def adminEditSlug = 'routing_example_adminedit'

    def adminAddSlug = 'routing_example_adminadd'

    def adminDoEditSlug = 'routing_example_admindoedit'

    def adminDoDeleteSlug = 'routing_example_admindodelete'

    def frontListSlug = 'routing_example_frontlist'

    def frontDetailSlug = 'routing_example_frontdetail'

    TaskService taskService

    RoutingService routingService

    def onSuccess = { moduleResponse, moduleRequest, page ->
        Page redirectPage = routingService.getSingleton(adminListSlug);
        moduleResponse.addFlash('message', 'Task byl úspěšně přidán')
        moduleResponse.addRedirect(redirectPage)
    }
	
	public def getTaskList(Page page, Request moduleRequest, Response moduleResponse) {
		return [tasks: taskService.findAll(moduleRequest.getSession())]
	}

    public def edit(Page page, Request moduleRequest, Response moduleResponse)
    {
        Task task;
        if (moduleRequest.action == 'add') {
            task = taskService.add(moduleRequest.slug, moduleRequest.name, moduleRequest.getSession()) as Task
        } else {
            task = taskService.edit(moduleRequest.slug, moduleRequest.name, moduleRequest.done, moduleRequest.getSession()) as Task
        }
        moduleResponse.addCall(onSuccess)
        return [task: task]
    }

    public def delete(Page page, Request moduleRequest, Response moduleResponse)
    {
        taskService.delete(moduleRequest.slug, moduleRequest.getSession())
    }

}
