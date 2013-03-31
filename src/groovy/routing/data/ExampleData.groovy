package routing.data

import routing.domain.ModuleControl
import routing.domain.PageType
import routing.example.control.TaskModuleControl

class ExampleData {

    public static def load(def ctx, def defaultHost) {
        TaskModuleControl taskModuleControl = ctx.'routing.example.control.TaskModuleControl'

        def moduleControls = [
                taskModuleControl: new ModuleControl(
                        className: routing.example.control.TaskModuleControl.class.getName(),
                        slug: 'routing_example_task'
                ),
        ]
        moduleControls*.value*.save();

        def pageTypes = [
                frontTaskListPageType: new PageType(
                        slug: taskModuleControl.frontListSlug,
                        description: 'Task list on front',
                        singleton: false,
                        templateName: '/routing/example/front/list',
                        moduleControls: moduleControls*.value,
                        registeredCalls: []),
        ]
    }
}
