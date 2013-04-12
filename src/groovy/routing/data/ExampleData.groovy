package routing.data

import routing.domain.ModuleControl
import routing.domain.PageType
import routing.example.control.TaskModuleControl
import routing.domain.RegisteredCall
import routing.domain.Page
import routing.domain.UrlTypeEnum
import routing.domain.RequestTypeEnum
import routing.domain.HttpMethodEnum

class ExampleData {

    public static def load(def ctx, def defaultHost, def fixturesData) {
        TaskModuleControl taskModuleControl = ctx.'routing.example.control.TaskModuleControl'

        def moduleControls = [
                taskModuleControl: new ModuleControl(
                        className: routing.example.control.TaskModuleControl.class.getName(),
                        slug: 'routing_example_task'
                ),
        ]
        moduleControls*.value*.save();

        def allModuleControls = moduleControls*.value + fixturesData.moduleControls*.value

        def pageTypes = [
                frontTaskListPageType: new PageType(
                        slug: taskModuleControl.frontListSlug,
                        description: 'Task list on front',
                        singleton: false,
                        templateName: '/routing/example/front/list',
                        moduleControls: allModuleControls,
                        registeredCalls: []),
                adminTaskListPageType: new PageType(
                        slug: taskModuleControl.adminListSlug,
                        description: 'Task list in admin',
                        singleton: true,
                        templateName: '/routing/example/admin/list',
                        moduleControls: allModuleControls,
                        parent: fixturesData.pageTypes.adminHomepagePageType,
                        registeredCalls: [
                            new RegisteredCall(moduleControl: moduleControls['taskModuleControl'], methodName: 'getTaskList')
                        ]),
                adminTaskEditPageType: new PageType(
                        slug: taskModuleControl.adminEditSlug,
                        description: 'Task edit in admin',
                        singleton: true,
                        templateName: '/routing/example/admin/edit',
                        moduleControls: allModuleControls,
                        parent: fixturesData.pageTypes.adminHomepagePageType,
                        registeredCalls: []),
                adminTaskAddPageType: new PageType(
                        slug: taskModuleControl.adminAddSlug,
                        description: 'Task add in admin',
                        singleton: true,
                        templateName: '/routing/example/admin/add',
                        moduleControls: allModuleControls,
                        parent: fixturesData.pageTypes.adminHomepagePageType,
                        registeredCalls: []),
                adminTaskDoEditPageType: new PageType(
                        slug: taskModuleControl.adminDoEditSlug,
                        description: 'Task do edit in admin',
                        singleton: true,
                        templateName: '/routing/example/admin/edit',
                        moduleControls: allModuleControls,
                        parent: fixturesData.pageTypes.adminHomepagePageType,
                        registeredCalls: [
                                new RegisteredCall(moduleControl: moduleControls['taskModuleControl'], methodName: 'edit')
                        ]),
                adminTaskDoDeletePageType: new PageType(
                        slug: taskModuleControl.adminDoDeleteSlug,
                        description: 'Task delete in admin',
                        singleton: true,
                        templateName: '/routing/example/admin/list',
                        moduleControls: allModuleControls,
                        parent: fixturesData.pageTypes.adminHomepagePageType,
                        registeredCalls: [
                                new RegisteredCall(moduleControl: moduleControls['taskModuleControl'], methodName: 'delete')
                        ]),
        ]

        pageTypes*.value*.save();

        def pages = [
                adminTaskListPage: new Page(
                        host: defaultHost,
                        urlPart: '/task-list',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.adminTaskListPageType,
                ),

                adminTaskEditPage: new Page(
                        host: defaultHost,
                        urlPart: '/edit',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.adminTaskEditPageType
                ),

                adminTaskAddPage: new Page(
                        host: defaultHost,
                        urlPart: '/add',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.adminTaskAddPageType
                ),

                adminTaskDoEditPage: new Page(
                        host: defaultHost,
                        urlPart: '/do-edit',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.POST,
                        pageType: pageTypes.adminTaskDoEditPageType
                ),

                adminTaskDoDeletePage: new Page(
                        host: defaultHost,
                        urlPart: '/do-delete',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.POST,
                        pageType: pageTypes.adminTaskDoDeletePageType
                ),
        ]

        pages*.value*.save();

        return [pages: pages, pageTypes: pageTypes, moduleControls: moduleControls]
    }
}
