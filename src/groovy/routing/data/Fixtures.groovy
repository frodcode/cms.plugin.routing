package routing.data

import routing.domain.HttpMethodEnum
import routing.domain.ModuleControl
import routing.domain.Page
import routing.domain.PageType
import routing.domain.RegisteredCall
import routing.domain.RequestTypeEnum
import routing.domain.UrlTypeEnum
import routing.domain.auth.AuthRole
import routing.domain.auth.AuthUser
import routing.domain.auth.AuthUserAuthRole
import routing.auth.AclService

class Fixtures {
	public static def load(def ctx, def defaultHost) {
		def authModuleControl = ctx.'routing.control.auth.AuthModuleControl'
        AclService aclService = ctx.aclService

        aclService.adminHomepageSlug = authModuleControl.adminHomepageSlug
        aclService.notEnoughPrivilegesSlug = authModuleControl.loginSlug
        aclService.notLoggedInSlug = authModuleControl.loginSlug

		def adminRole = new AuthRole(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new AuthRole(authority: 'ROLE_USER').save(flush: true)

		def testUser = new AuthUser(username: 'admin', enabled: true, password: 'password').save(flush: true)

		AuthUserAuthRole.create testUser, adminRole, true

		def moduleControls = [
			routingMc : new ModuleControl(
			className: routing.control.RoutingModuleControl.class.getName(),
			slug: 'routing'
			),
			authMc : new ModuleControl(
			className: routing.control.auth.AuthModuleControl.class.getName(),
			slug: 'auth'
			),
		]
		moduleControls*.value*.save();


		def pageTypes = [
			homepagePageType : new PageType(
			slug : 'homepage',
			description: 'Homepage',
			singleton: true,
			templateName: '/routing/front/homepage',
			moduleControls: moduleControls*.value,
			registeredCalls : []),

			adminHomepagePageType : new PageType(
			slug : authModuleControl.adminHomepageSlug,
			description: 'Admin article page',
			singleton: true,
			templateName: '/routing/admin/homepage',
			moduleControls: moduleControls*.value,
			registeredCalls : [],
			),

			loginPageType : new PageType(
			slug : authModuleControl.loginSlug,
			description: 'Admin login page',
			singleton: true,
			templateName: '/routing/admin/login',
			moduleControls: moduleControls*.value,
			registeredCalls : []),

			doLoginPageType : new PageType(
			slug : authModuleControl.doLoginSlug,
			description: 'Admin confirm login',
			singleton: true,
			templateName: '/routing/admin/do-login',
			moduleControls: moduleControls*.value,
			registeredCalls : new RegisteredCall(
			moduleControl: moduleControls['authMc'],
			methodName: 'doLogin'
			),
			),
		]
		pageTypes*.value*.save();
		def pages = [
			homepage : new Page(
			host: defaultHost,
			urlPart: '/',
			urlType: UrlTypeEnum.FROM_PARENT,
			requestType: RequestTypeEnum.REGULAR,
			httpMethod: HttpMethodEnum.GET,
			pageType: pageTypes.homepagePageType
			),

			adminHomepage : new Page(
			host: defaultHost,
			urlPart: '/admin',
			urlType: UrlTypeEnum.FROM_PARENT,
			requestType: RequestTypeEnum.REGULAR,
			httpMethod: HttpMethodEnum.GET,
			pageType: pageTypes.adminHomepagePageType,
			authRoles: adminRole
			),
		]
		pages['adminLogin'] = new Page(
				parent: pages.adminHomepage,
				urlPart: '/login',
				pageType: pageTypes.loginPageType,
				authRoles: []);
		pages['adminDoLogin'] = new Page(
				parent: pages.adminHomepage,
				urlPart: '/do-login',
				httpMethod: HttpMethodEnum.POST,
				pageType: pageTypes.doLoginPageType,
				authRoles: []);
		pages*.value*.save();
		return [pages: pages, pageTypes: pageTypes, moduleControls: moduleControls]
	}
}
