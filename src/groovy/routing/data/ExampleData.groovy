package routing.data

import frod.routing.domain.PageType
import frod.routing.domain.Page
import frod.routing.domain.UrlTypeEnum
import frod.routing.domain.RequestTypeEnum
import frod.routing.domain.HttpMethodEnum
import frod.routing.domain.auth.AuthRole
import frod.routing.domain.auth.AuthUser
import frod.routing.domain.auth.AuthUserAuthRole
import frod.routing.domain.example.Task

class ExampleData {

    public static def load(def ctx, def defaultHost, def fixturesData) {
        def adminRole = new AuthRole(authority: 'ROLE_ADMIN').save(flush: true)
        def superAdminRole = new AuthRole(authority: 'ROLE_SUPERADMIN').save(flush: true)
        def userRole = new AuthRole(authority: 'ROLE_USER').save(flush: true)

        def adminUser = new AuthUser(username: 'admin', enabled: true, password: 'password').save(flush: true)
        def testSuperAdmin = new AuthUser(username: 'superadmin', enabled: true, password: 'password').save(flush: true)

        AuthUserAuthRole.create adminUser, adminRole, true
        AuthUserAuthRole.create testSuperAdmin, superAdminRole, true
        def pageTypes = [
                frontTaskListPageType: new PageType(
                        slug: 'task_list',
                        description: 'Task list on front',
                        singleton: false,
                        controller: 'Task',
                        action: 'list'),
                homepagePageType: new PageType(
                        slug: 'homepage',
                        description: 'Homepage',
                        singleton: true,
                        controller: 'Index',
                        action: 'index'),
                frontTaskDetailPageType: new PageType(
                        slug: 'task_detail',
                        description: 'Task detail on front',
                        singleton: false,
                        controller: 'Task',
                        action: 'detail'),
        ]

        pageTypes*.value*.save();

        def pages = [
                homepage: new Page(
                        domain: defaultHost,
                        urlPart: '/',
                        urlType: UrlTypeEnum.ROOT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.homepagePageType
                ),
        ]

        pages.taskListPage = new Page(
                domain: defaultHost,
                urlPart: '/task-list',
                urlType: UrlTypeEnum.FROM_PARENT,
                requestType: RequestTypeEnum.REGULAR,
                httpMethod: HttpMethodEnum.GET,
                pageType: pageTypes.frontTaskListPageType,
                parent: pages.homepage,
        )

        pages*.value*.save();

        def tasks = [
                createRouting: new Task(
                        name: 'Vytvořit routování',
                        page: new Page(
                                urlPart: '/vytvorit-routovani',
                                pageType: pageTypes.frontTaskDetailPageType,
                                parent: pages.homepage
                        )
                ),
                buyServer: new Task(
                        name: 'Pořídit server',
                        page: new Page(
                                urlPart: '/poridit-server',
                                pageType: pageTypes.frontTaskDetailPageType,
                                parent: pages.homepage
                        )
                ),
                testApplication: new Task(
                        name: 'Otestovat aplikaci',
                        page: new Page(
                                urlPart: '/otestovat-aplikaci',
                                pageType: pageTypes.frontTaskDetailPageType,
                                parent: pages.homepage
                        )
                )
        ]
        tasks*.value*.page*.save(flush: true, failOnError: true)
        tasks*.value*.save(failOnError: true)

        return [pages: pages, pageTypes: pageTypes, tasks: tasks]
    }
}
