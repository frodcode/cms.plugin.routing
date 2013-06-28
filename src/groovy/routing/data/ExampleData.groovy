package routing.data

import ford.routing.domain.PageType
import ford.routing.domain.Page
import ford.routing.domain.UrlTypeEnum
import ford.routing.domain.RequestTypeEnum
import ford.routing.domain.HttpMethodEnum
import ford.routing.domain.auth.AuthRole
import ford.routing.domain.auth.AuthUser
import ford.routing.domain.auth.AuthUserAuthRole

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

        return [pages: pages, pageTypes: pageTypes]
    }
}
