package frod.routing.service.example

import frod.routing.domain.example.Task
import frod.routing.domain.Page
import frod.routing.service.PageCommand

/**
 * User: freeman
 * Date: 1.7.13
 */
@grails.validation.Validateable
class TaskCommand {

    Integer taskId

    String name

    Boolean done

    static constraints = {
        // todo dodelat
        importFrom Task
        taskId(nullable: true, validator: { val, obj ->
            if (obj.taskId) {
                return !!Task.get(obj.taskId)
            }
            return true;
        })
    }

    Task getTask() {
        if (!taskId) {
            throw new IllegalStateException('Cannot get task because no id is available')
        }
        return Task.get(taskId)
    }

}
