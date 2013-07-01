package frod.routing.service.example

import frod.routing.domain.example.Task

/**
 * User: freeman
 * Date: 1.7.13
 */
@grails.validation.Validateable
class TaskCommand {
    String name

    static constraints = {
        // todo dodelat
        importFrom Task
    }

    def getDone() {
        return true
    }

}
