package routing.data

import domain.routing.HttpMethodEnum
import domain.routing.ModuleControl
import domain.routing.Page
import domain.routing.PageType
import domain.routing.RegisteredCall
import domain.routing.RequestTypeEnum
import domain.routing.UrlTypeEnum

class Fixtures {
	public static def load(def ctx, def defaultHost) {
		def atuhModuleControl = ctx.'routing.auth.AuthModuleControl'
		def moduleControls = [
			ModuleControl routingMc = new ModuleControl(
			className: routing.control.RoutingModuleControl.class.getName(),
			slug: 'routing'
			),
			ModuleControl authMc = new ModuleControl(
			className: routing.auth.AuthModuleControl.class.getName(),
			slug: 'auth'
			),
		]
		moduleControls*.save();

		def pageTypes = [
			homepagePageType : new PageType(
			slug : 'homepage',
			description: 'Homepage',
			singleton: true,
			templateName: '/routing/front/homepage',
			moduleControls: moduleControls,
			registeredCalls : []),

			adminHomepagePageType : new PageType(
			slug : 'admin_homepage',
			description: 'Admin article page',
			singleton: true,
			templateName: '/routing/admin/homepage',
			moduleControls: moduleControls,
			registeredCalls : []),

			loginPageType : new PageType(
			slug : atuhModuleControl.loginSlug,
			description: 'Admin login page',
			singleton: true,
			templateName: '/routing/admin/login',
			moduleControls: moduleControls,
			registeredCalls : []),

			doLoginPageType : new PageType(
			slug : atuhModuleControl.doLoginSlug,
			description: 'Admin confirm login',
			singleton: true,
			templateName: '/routing/admin/do-login',
			moduleControls: moduleControls,
			registeredCalls : new RegisteredCall(
			moduleControl: authMc,
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
			pageType: pageTypes.adminHomepagePageType
			),
		]
		pages['adminLogin'] = new Page(
				parent: pages.adminHomepage,
				urlPart: '/login',
				pageType: pageTypes.loginPageType
				);
		pages['adminDoLogin'] = new Page(
				parent: pages.adminHomepage,
				urlPart: '/do-login',
				httpMethod: HttpMethodEnum.POST,
				pageType: pageTypes.doLoginPageType
				);
		pages*.value*.save();
		return [pages: pages, pageTypes: pageTypes, moduleControls: [routingMc:routingMc]]
	}
}
